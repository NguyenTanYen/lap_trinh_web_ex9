package jwt.lap_trinh_web_09_01.service;

import jwt.lap_trinh_web_09_01.entity.User;
import jwt.lap_trinh_web_09_01.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtTokenProvider jwtTokenProvider;

    public String generateToken(User user) {
        // Tạo authentication object từ user
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.getName().name()))
                        .toList())
                .build();

        Authentication authentication = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        return jwtTokenProvider.generateJwtToken(authentication);
    }
}
