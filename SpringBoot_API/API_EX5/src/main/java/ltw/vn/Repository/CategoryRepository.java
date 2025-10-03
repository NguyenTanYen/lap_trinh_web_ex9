package ltw.vn.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ltw.vn.Entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Category> findByCategorynameContaining(String name);
	List<Category> findByCategoryNameContaining(String name);
	Optional<Category> findByCategoryName(String name);
	Page<Category> findByCategoryNameContaining(String name, Pageable pageable);

}
