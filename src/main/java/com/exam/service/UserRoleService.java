package com.exam.service;

import java.util.List;

import com.exam.model.UserRole;

public interface UserRoleService {

	public List<UserRole> getRole(Long userId);

	public UserRole updateRole(UserRole userRole);

}
