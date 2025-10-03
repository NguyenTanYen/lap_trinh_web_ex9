package jwt.lap_trinh_web_09_01.controller;

import jwt.lap_trinh_web_09_01.dto.LoginRequest;
import jwt.lap_trinh_web_09_01.dto.RegisterRequest;
import jwt.lap_trinh_web_09_01.dto.SimpleJwtResponse;
import jwt.lap_trinh_web_09_01.entity.ERole;
import jwt.lap_trinh_web_09_01.entity.Role;
import jwt.lap_trinh_web_09_01.entity.User;
import jwt.lap_trinh_web_09_01.repository.UserRepository;
import jwt.lap_trinh_web_09_01.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User đã tồn tại");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        
        // Thêm role mặc định cho user
        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setName(ERole.ROLE_USER);
        roles.add(userRole);
        user.setRoles(roles);
        
        userRepository.save(user);
        return ResponseEntity.ok("Đăng ký thành công!");
    }

    // Đăng nhập -> sinh JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new SimpleJwtResponse(token));
    }
}

