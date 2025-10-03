package jwt.lap_trinh_web_09_01.dto;

import jwt.lap_trinh_web_09_01.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String username;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private String shippingPhone;
    private String notes;
    private List<OrderItemResponse> orderItems;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.totalAmount = order.getTotalAmount();
        this.status = order.getStatus().name();
        this.orderDate = order.getOrderDate();
        this.shippingAddress = order.getShippingAddress();
        this.shippingPhone = order.getShippingPhone();
        this.notes = order.getNotes();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemResponse {
        private Long id;
        private String bookTitle;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal totalPrice;

        public OrderItemResponse(jwt.lap_trinh_web_09_01.entity.OrderItem orderItem) {
            this.id = orderItem.getId();
            this.bookTitle = orderItem.getBook().getTitle();
            this.quantity = orderItem.getQuantity();
            this.unitPrice = orderItem.getUnitPrice();
            this.totalPrice = orderItem.getTotalPrice();
        }
    }
}

