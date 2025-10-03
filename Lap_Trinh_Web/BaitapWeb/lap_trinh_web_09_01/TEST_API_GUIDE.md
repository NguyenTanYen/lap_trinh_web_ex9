# 🛠️ Hướng dẫn Test API sau khi fix lỗi 401

## ✅ Các thay đổi đã thực hiện:

### 1. **SecurityConfig.java** - Mở API login và register
```java
.authorizeHttpRequests(auth ->
    auth.requestMatchers("/auth/**").permitAll()   // cho phép login, register không cần token
        .anyRequest().authenticated()              // các request khác phải có JWT
);
```

### 2. **AuthController.java** - Tạo API đăng ký và đăng nhập
- `POST /auth/register` - Đăng ký tài khoản
- `POST /auth/login` - Đăng nhập và lấy JWT token

### 3. **UserController.java** - API test JWT
- `GET /users/me` - Lấy thông tin user hiện tại (cần JWT)
- `GET /users` - Lấy tất cả user (cần JWT)

### 4. **DTO Classes**
- `RegisterRequest` - DTO cho đăng ký
- `LoginRequest` - DTO cho đăng nhập  
- `SimpleJwtResponse` - DTO trả về token

### 5. **JwtService.java** - Service tạo JWT token

---

## 🧪 Test bằng Postman/curl:

### **Bước 1: Đăng ký tài khoản**
```bash
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "username": "tanye",
  "password": "123456",
  "email": "tanye@gmail.com"
}
```

**Kết quả mong đợi:**
```json
"Đăng ký thành công!"
```

### **Bước 2: Đăng nhập lấy token**
```bash
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "tanye",
  "password": "123456"
}
```

**Kết quả mong đợi:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### **Bước 3: Test API cần JWT (thành công)**
```bash
GET http://localhost:8080/users/me
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

**Kết quả mong đợi:**
```json
{
  "id": 1,
  "username": "tanye",
  "email": "tanye@gmail.com",
  "password": "$2a$10$...",
  "fullName": null,
  "phone": null,
  "address": null,
  "roles": []
}
```

### **Bước 4: Test API không có token (sẽ bị 401)**
```bash
GET http://localhost:8080/users/me
```

**Kết quả mong đợi:**
```json
{
  "path": "/error",
  "error": "Unauthorized", 
  "message": "Full authentication is required to access this resource",
  "status": 401
}
```

---

## 🎯 Kết quả mong đợi:

✅ **Không còn báo Unauthorized** cho các API public (`/auth/**`)

✅ **Chỉ khi quên gửi Authorization: Bearer <token>** mới bị 401

✅ **Nếu token hết hạn** (dựa trên `security.jwt.expiration-time`), phải login lại

---

## 📝 Lưu ý:

- Các API `/auth/**` là **public**, không cần token
- Các API khác cần **JWT token** trong header `Authorization: Bearer <token>`
- Token có thời hạn theo cấu hình trong `application.properties`
- CORS đã được cấu hình đúng để hỗ trợ frontend
