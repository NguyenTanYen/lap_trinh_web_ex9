package jwt.lap_trinh_web_09_01.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @Valid
    @NotEmpty
    private List<OrderItemRequest> orderItems;

    @Size(max = 200)
    private String shippingAddress;

    @Size(max = 100)
    private String shippingPhone;

    @Size(max = 500)
    private String notes;

    @Data
    public static class OrderItemRequest {
        private Long bookId;
        private Integer quantity;
    }
}

