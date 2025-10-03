package nguyentanyen.lap_trinh_web_ex09.repository;

import nguyentanyen.lap_trinh_web_ex09.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}