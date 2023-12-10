package com.exam.service;

import java.util.List;
import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	public User createUser(User user, Set<UserRole> userRoles);

	public User getUserByUserName(String userName);

	public boolean deleteUser(Long userId);

	public List<User> getUsers();

	public boolean updatePasswordByEmail(String email, String password);

}
