package jwt.lap_trinh_web_09_01.repository;

import jwt.lap_trinh_web_09_01.entity.ERole;
import jwt.lap_trinh_web_09_01.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}


