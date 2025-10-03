package jwt.lap_trinh_web_09_01.repository;

import jwt.lap_trinh_web_09_01.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}


