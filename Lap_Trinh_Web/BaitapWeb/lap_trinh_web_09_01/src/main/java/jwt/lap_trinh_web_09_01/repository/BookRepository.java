package jwt.lap_trinh_web_09_01.repository;

import jwt.lap_trinh_web_09_01.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByCategoryId(Long categoryId, Pageable pageable);
    
    @Query("SELECT b FROM Book b WHERE " +
           "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) AND " +
           "(:categoryId IS NULL OR b.category.id = :categoryId) AND " +
           "(:minPrice IS NULL OR b.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR b.price <= :maxPrice)")
    Page<Book> searchBooks(@Param("title") String title,
                          @Param("author") String author,
                          @Param("categoryId") Long categoryId,
                          @Param("minPrice") Double minPrice,
                          @Param("maxPrice") Double maxPrice,
                          Pageable pageable);
    
    List<Book> findByStockGreaterThan(Integer stock);
}


