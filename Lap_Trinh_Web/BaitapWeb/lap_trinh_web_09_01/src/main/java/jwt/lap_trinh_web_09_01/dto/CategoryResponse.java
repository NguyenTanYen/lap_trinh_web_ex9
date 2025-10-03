package jwt.lap_trinh_web_09_01.dto;

import jwt.lap_trinh_web_09_01.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
}


