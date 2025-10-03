package jwt.lap_trinh_web_09_01.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
