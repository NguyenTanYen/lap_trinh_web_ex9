package jwt.lap_trinh_web_09_01.controller;

import jakarta.validation.Valid;
import jwt.lap_trinh_web_09_01.dto.OrderRequest;
import jwt.lap_trinh_web_09_01.dto.OrderResponse;
import jwt.lap_trinh_web_09_01.entity.Order;
import jwt.lap_trinh_web_09_01.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getUserOrders() {
        return ResponseEntity.ok(orderService.getUserOrders());
    }

    @GetMapping("/my-orders/paged")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Page<OrderResponse>> getUserOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getUserOrders(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/all/paged")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<OrderResponse>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long id, 
            @RequestParam Order.OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
}

