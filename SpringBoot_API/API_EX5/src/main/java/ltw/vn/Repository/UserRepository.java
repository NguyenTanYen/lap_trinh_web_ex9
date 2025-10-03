package ltw.vn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ltw.vn.Entity.Users;

public interface UserRepository  extends JpaRepository<Users, Long> {
	 Users findByUserName(String userName);

}
