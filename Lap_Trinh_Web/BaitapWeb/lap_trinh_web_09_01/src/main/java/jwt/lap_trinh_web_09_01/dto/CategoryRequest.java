package jwt.lap_trinh_web_09_01.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;
}


