# Spring Security Demo Application

A comprehensive Spring Boot application demonstrating Spring Security features with role-based access control, authentication, and authorization.

## Features

- **Authentication**: Secure user login with custom user details service
- **Authorization**: Role-based access control (ADMIN, USER roles)
- **Product Management**: CRUD operations for products with role-based permissions
- **Modern UI**: Bootstrap 5 with responsive design and Font Awesome icons
- **Database Integration**: MySQL database with JPA/Hibernate

## Technology Stack

- **Backend**: Spring Boot 3.5.6, Spring Security 6, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5, Font Awesome
- **Database**: MySQL
- **Build Tool**: Maven
- **Java Version**: 17

## Project Structure

```
src/main/java/nguyentanyen/lap_trinh_web_ex09/
├── config/
│   ├── SecurityConfig.java          # Security configuration
│   └── DataInitializer.java         # Data initialization
├── controller/
│   ├── HomeController.java          # Home page controller
│   └── ProductController.java       # Product management controller
├── entity/
│   ├── User.java                    # User entity
│   ├── Role.java                    # Role entity
│   └── Product.java                 # Product entity
├── repository/
│   ├── UserRepository.java          # User data access
│   ├── RoleRepository.java          # Role data access
│   └── ProductRepository.java       # Product data access
└── service/
    ├── CustomUserDetails.java       # Custom user details implementation
    └── CustomUserDetailsService.java # User details service
```

## Security Configuration

The application implements the following security features:

- **Authentication**: Form-based login with custom user details service
- **Authorization**: Role-based access control
  - `/admin/**` - Requires ADMIN role
  - `/user/**` - Requires USER or ADMIN role
  - Public access to home, login, and static resources
- **Password Encoding**: BCrypt password encoder
- **Session Management**: Configurable logout and session handling

## Demo Credentials

The application comes with pre-configured demo users:

- **Admin User**: 
  - Username: `admin`
  - Password: `admin`
  - Role: ADMIN (can manage products)

- **Regular User**:
  - Username: `user`
  - Password: `user`
  - Role: USER (can view products)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

### Database Setup

1. Create a MySQL database named `spring_security6_demo`
2. Update the database credentials in `application.properties` if needed

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Open your browser and navigate to `http://localhost:8080`

### Default Configuration

The application uses the following default configuration:

- **Server Port**: 8080
- **Database**: MySQL (localhost:3306)
- **Database Name**: spring_security6_demo
- **Username**: root
- **Password**: Tanyen909@

## Application Pages

- **Home Page** (`/`): Welcome page with user information
- **Login Page** (`/login`): User authentication
- **Products List** (`/products`): View all products
- **Add Product** (`/products/add`): Add new product (ADMIN only)
- **Edit Product** (`/products/edit/{id}`): Edit product (ADMIN only)
- **Access Denied** (`/access-denied`): Unauthorized access page

## Security Features Demonstrated

1. **Form-based Authentication**: Custom login page with error handling
2. **Role-based Authorization**: Different access levels for ADMIN and USER roles
3. **Password Security**: BCrypt password encoding
4. **Session Management**: Secure logout and session handling
5. **CSRF Protection**: Configurable CSRF protection
6. **Method-level Security**: `@PreAuthorize` annotations for fine-grained control

## Customization

### Adding New Roles

1. Create a new role in the database or through DataInitializer
2. Update SecurityConfig to define access rules for the new role
3. Use `@PreAuthorize` annotations in controllers for method-level security

### Adding New Features

1. Create entity classes for new domain objects
2. Create repository interfaces extending JpaRepository
3. Create service classes for business logic
4. Create controllers with appropriate security annotations
5. Create Thymeleaf templates for the UI

## Troubleshooting

### Common Issues

1. **Database Connection**: Ensure MySQL is running and credentials are correct
2. **Port Conflicts**: Change the server port in `application.properties` if 8080 is occupied
3. **Authentication Issues**: Check that users exist in the database with correct roles

### Logs

Enable debug logging by adding the following to `application.properties`:

```properties
logging.level.org.springframework.security=DEBUG
logging.level.nguyentanyen.lap_trinh_web_ex09=DEBUG
```

## License

This project is for educational purposes and demonstrates Spring Security best practices.

