# Hướng dẫn Test API Authentication

## Các API Public (không cần token)

### 1. Đăng ký tài khoản mới
```bash
POST /api/auth/signup
Content-Type: application/json

{
    "username": "testuser",
    "email": "test@example.com",
    "password": "123456",
    "fullName": "Test User",
    "phone": "0123456789",
    "address": "123 Test Street"
}
```

### 2. Đăng nhập để lấy token
```bash
POST /api/auth/signin
Content-Type: application/json

{
    "username": "testuser",
    "password": "123456"
}
```

**Response sẽ trả về:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer",
    "id": 1,
    "username": "testuser",
    "email": "test@example.com",
    "fullName": "Test User",
    "roles": ["ROLE_USER"]
}
```

## Các API Protected (cần token)

### 3. Lấy thông tin user hiện tại
```bash
GET /api/users/me
Authorization: Bearer <token>
```

### 4. Lấy danh sách tất cả users (cần quyền USER hoặc ADMIN)
```bash
GET /api/users
Authorization: Bearer <token>
```

## Cách sử dụng với Postman/curl

1. **Đăng ký tài khoản:**
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

2. **Đăng nhập:**
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "123456"
  }'
```

3. **Sử dụng token để gọi API protected:**
```bash
curl -X GET http://localhost:8080/api/users/me \
  -H "Authorization: Bearer <token_from_step_2>"
```

## Lưu ý quan trọng

- Các API `/api/auth/**` là public, không cần token
- Các API `/api/users/**` cần token và quyền USER hoặc ADMIN
- Token phải được gửi trong header `Authorization: Bearer <token>`
- Nếu không có token hoặc token không hợp lệ, sẽ nhận lỗi 401 Unauthorized
