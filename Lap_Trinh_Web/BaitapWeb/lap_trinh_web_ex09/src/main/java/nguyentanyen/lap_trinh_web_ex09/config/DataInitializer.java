package nguyentanyen.lap_trinh_web_ex09.config;

import nguyentanyen.lap_trinh_web_ex09.entity.Product;
import nguyentanyen.lap_trinh_web_ex09.entity.Role;
import nguyentanyen.lap_trinh_web_ex09.entity.User;
import nguyentanyen.lap_trinh_web_ex09.repository.ProductRepository;
import nguyentanyen.lap_trinh_web_ex09.repository.RoleRepository;
import nguyentanyen.lap_trinh_web_ex09.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        // Initialize users
        if (userRepository.count() == 0) {
            // Admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEnabled(true);

            Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);
            if (adminRole != null) {
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                admin.setRoles(adminRoles);
                userRepository.save(admin);
            }

            // Regular user
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user"));
            user.setEnabled(true);

            Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
            if (userRole != null) {
                Set<Role> userRoles = new HashSet<>();
                userRoles.add(userRole);
                user.setRoles(userRoles);
                userRepository.save(user);
            }
        }

        // Initialize sample products
        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setName("Laptop");
            product1.setPrice(999.99);
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setName("Smartphone");
            product2.setPrice(699.99);
            productRepository.save(product2);

            Product product3 = new Product();
            product3.setName("Tablet");
            product3.setPrice(399.99);
            productRepository.save(product3);

            Product product4 = new Product();
            product4.setName("Headphones");
            product4.setPrice(199.99);
            productRepository.save(product4);
        }
    }
}
