package jwt.lap_trinh_web_09_01.repository;

import jwt.lap_trinh_web_09_01.entity.Order;
import jwt.lap_trinh_web_09_01.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByOrderDateDesc(User user);
    Page<Order> findByUserOrderByOrderDateDesc(User user, Pageable pageable);
    List<Order> findByStatus(Order.OrderStatus status);
    Page<Order> findByStatus(Order.OrderStatus status, Pageable pageable);
}


