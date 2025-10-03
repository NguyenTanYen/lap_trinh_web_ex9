package ltw.vn.Service;

import ltw.vn.Entity.Users;

public interface UserService {
	Users findByUsername(String username);

	Users updateProfile(Users user);
}
