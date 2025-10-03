package jwt.lap_trinh_web_09_01.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookRequest {
    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 100)
    private String author;

    @Size(max = 100)
    private String publisher;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull
    private Integer stock;

    @Size(max = 1000)
    private String description;

    @Size(max = 500)
    private String imageUrl;

    @Size(max = 20)
    private String isbn;

    private Integer publicationYear;

    private Long categoryId;
}


