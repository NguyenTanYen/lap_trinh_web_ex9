package jwt.lap_trinh_web_09_01.service;

import jwt.lap_trinh_web_09_01.dto.CategoryRequest;
import jwt.lap_trinh_web_09_01.dto.CategoryResponse;
import jwt.lap_trinh_web_09_01.entity.Category;
import jwt.lap_trinh_web_09_01.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return new CategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return new CategoryResponse(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}

