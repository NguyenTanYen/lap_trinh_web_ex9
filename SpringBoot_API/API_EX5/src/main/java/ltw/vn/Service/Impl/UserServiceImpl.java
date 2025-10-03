package ltw.vn.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ltw.vn.Entity.Users;
import ltw.vn.Repository.UserRepository;
import ltw.vn.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public Users findByUsername(String username) {
		return userRepo.findByUserName(username);
	}

	@Override
	public Users updateProfile(Users user) {
		return userRepo.save(user);
	}
}
