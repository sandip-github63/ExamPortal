package com.exam.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.exceptions.CustomException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {

		User u1 = null;

		System.out.println("inside createUser method...");

		User u = this.userRepo.findByUserName(user.getUserName());

		try {

			if (u != null) {
				System.out.println("user already exit...");

				throw new CustomException("user is already exits...");

			} else {
				for (UserRole uRole : userRoles) {

					roleRepo.save(uRole.getRole());

					System.out.println("user Roles save succssfully..." + uRole.getRole().getRoleId());

				}

				user.getuRole().addAll(userRoles);

				user.setPassword(passwordEncoder.encode(user.getPassword()));

				u1 = userRepo.save(user);

				System.out.println("user data save successfully..." + u1.getFirstName());

			}

		} catch (CustomException ex) {
			System.out.println(ex.getMessage());
		}

		return u1;
	}

	public User getUserByUserName(String userName) {
		User user = userRepo.findByUserName(userName);

		return user;
	}

	public boolean deleteUser(Long userId) {

		boolean result = false;

		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {

			userRepo.deleteById(userId);
			return result = true;
		}

		return result;

	}

	@Override
	public List<User> getUsers() {

		return this.userRepo.findAll();
	}

}
