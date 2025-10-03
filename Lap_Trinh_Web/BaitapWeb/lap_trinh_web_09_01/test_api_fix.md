# Test API sau khi sửa lỗi 401

## Các thay đổi đã thực hiện:

1. **Sửa cấu hình CORS**: Tạo `CorsConfig.java` riêng và cập nhật `SecurityConfig.java`
2. **Cấu hình CORS đúng cách**: Thay vì disable CORS, sử dụng configuration source
3. **Đảm bảo các API public hoạt động**: `/api/auth/**` được permitAll()

## Test các API:

### 1. Test API đăng ký (không cần token)
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "123456",
    "fullName": "Test User",
    "phone": "0123456789",
    "address": "123 Test Street"
  }'
```

**Kết quả mong đợi**: Status 200, message "User registered successfully!"

### 2. Test API đăng nhập (không cần token)
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "123456"
  }'
```

**Kết quả mong đợi**: Status 200, trả về JWT token

### 3. Test API lấy thông tin user (cần token)
```bash
curl -X GET http://localhost:8080/api/users/me \
  -H "Authorization: Bearer <token_from_step_2>"
```

**Kết quả mong đợi**: Status 200, trả về thông tin user

### 4. Test API không có token (sẽ bị 401)
```bash
curl -X GET http://localhost:8080/api/users/me
```

**Kết quả mong đợi**: Status 401, message "Full authentication is required"

## Các lỗi đã được sửa:

1. **CORS Configuration**: Tạo cấu hình CORS riêng thay vì disable
2. **Security Filter Chain**: Cập nhật để sử dụng CORS configuration source
3. **Public API Access**: Đảm bảo `/api/auth/**` được permitAll()

## Lưu ý:
- Các API `/api/auth/**` là public, không cần token
- Các API khác cần token trong header `Authorization: Bearer <token>`
- CORS đã được cấu hình đúng để hỗ trợ frontend
