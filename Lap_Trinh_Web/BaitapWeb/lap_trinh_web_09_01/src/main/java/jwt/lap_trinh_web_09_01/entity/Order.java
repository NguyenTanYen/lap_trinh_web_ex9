package jwt.lap_trinh_web_09_01.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal totalAmount;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status = OrderStatus.PENDING;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();
    
    @Size(max = 200)
    private String shippingAddress;
    
    @Size(max = 100)
    private String shippingPhone;
    
    @Size(max = 500)
    private String notes;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> orderItems;
    
    public enum OrderStatus {
        PENDING,
        CONFIRMED,
        SHIPPING,
        DELIVERED,
        CANCELLED
    }
}


