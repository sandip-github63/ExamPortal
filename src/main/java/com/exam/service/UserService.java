package com.exam.service;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	public User createUser(User user, Set<UserRole> userRoles);

	public User getUserByUserName(String userName);

	public boolean deleteUser(Long userId);

}
