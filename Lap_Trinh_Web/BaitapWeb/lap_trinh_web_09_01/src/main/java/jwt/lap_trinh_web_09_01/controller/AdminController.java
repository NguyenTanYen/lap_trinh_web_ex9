package jwt.lap_trinh_web_09_01.controller;

import jwt.lap_trinh_web_09_01.entity.ERole;
import jwt.lap_trinh_web_09_01.entity.Role;
import jwt.lap_trinh_web_09_01.entity.User;
import jwt.lap_trinh_web_09_01.repository.RoleRepository;
import jwt.lap_trinh_web_09_01.repository.UserRepository;
import jwt.lap_trinh_web_09_01.service.BookService;
import jwt.lap_trinh_web_09_01.service.CategoryService;
import jwt.lap_trinh_web_09_01.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookService bookService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        // Simple stats - có thể mở rộng thêm
        long totalUsers = userRepository.count();
        long totalBooks = bookService.getAllBooks(org.springframework.data.domain.PageRequest.of(0, 1)).getTotalElements();
        long totalCategories = categoryService.getAllCategories().size();
        long totalOrders = orderService.getAllOrders(org.springframework.data.domain.PageRequest.of(0, 1)).getTotalElements();
        
        return ResponseEntity.ok(new AdminStats(totalUsers, totalBooks, totalCategories, totalOrders));
    }

    public static class AdminStats {
        public long totalUsers;
        public long totalBooks;
        public long totalCategories;
        public long totalOrders;

        public AdminStats(long totalUsers, long totalBooks, long totalCategories, long totalOrders) {
            this.totalUsers = totalUsers;
            this.totalBooks = totalBooks;
            this.totalCategories = totalCategories;
            this.totalOrders = totalOrders;
        }
    }
}

