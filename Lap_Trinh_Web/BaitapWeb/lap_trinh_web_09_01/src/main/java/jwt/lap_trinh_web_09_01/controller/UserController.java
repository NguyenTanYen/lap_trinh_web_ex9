package jwt.lap_trinh_web_09_01.controller;

import jwt.lap_trinh_web_09_01.entity.User;
import jwt.lap_trinh_web_09_01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    // Lấy thông tin user hiện tại từ JWT
    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(user);
    }

    // Lấy tất cả user (chỉ ai đã login mới được xem)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
