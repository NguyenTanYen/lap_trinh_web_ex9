package jwt.lap_trinh_web_09_01.service;

import jwt.lap_trinh_web_09_01.dto.OrderRequest;
import jwt.lap_trinh_web_09_01.dto.OrderResponse;
import jwt.lap_trinh_web_09_01.entity.*;
import jwt.lap_trinh_web_09_01.repository.BookRepository;
import jwt.lap_trinh_web_09_01.repository.OrderRepository;
import jwt.lap_trinh_web_09_01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setShippingPhone(orderRequest.getShippingPhone());
        order.setNotes(orderRequest.getNotes());
        order.setStatus(Order.OrderStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderRequest.OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Book book = bookRepository.findById(itemRequest.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found with id: " + itemRequest.getBookId()));

            if (book.getStock() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for book: " + book.getTitle());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(book.getPrice());
            orderItem.setTotalPrice(book.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            totalAmount = totalAmount.add(orderItem.getTotalPrice());
            order.getOrderItems().add(orderItem);

            // Update stock
            book.setStock(book.getStock() - itemRequest.getQuantity());
            bookRepository.save(book);
        }

        order.setTotalAmount(totalAmount);
        Order savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder);
    }

    public List<OrderResponse> getUserOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUserOrderByOrderDateDesc(user)
                .stream()
                .map(this::convertToOrderResponse)
                .collect(Collectors.toList());
    }

    public Page<OrderResponse> getUserOrders(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUserOrderByOrderDateDesc(user, pageable)
                .map(this::convertToOrderResponse);
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // Check if user has permission to view this order
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        if (!order.getUser().getUsername().equals(username) && 
            !authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Access denied");
        }

        return convertToOrderResponse(order);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToOrderResponse)
                .collect(Collectors.toList());
    }

    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(this::convertToOrderResponse);
    }

    public List<OrderResponse> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(this::convertToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return convertToOrderResponse(updatedOrder);
    }

    private OrderResponse convertToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse(order);
        List<OrderResponse.OrderItemResponse> itemResponses = order.getOrderItems()
                .stream()
                .map(OrderResponse.OrderItemResponse::new)
                .collect(Collectors.toList());
        response.setOrderItems(itemResponses);
        return response;
    }
}

