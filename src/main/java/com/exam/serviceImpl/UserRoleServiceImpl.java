package com.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRoleRepository;
import com.exam.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleRepository userRolerepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserRole> getRole(Long userId) {

		User u = new User();
		u.setUserId(userId);

		return this.userRolerepository.findByUser(u);
	}

	@Override
	public UserRole updateRole(UserRole userRole) {

		Role role = this.roleRepository.findByRoleName(userRole.getRole().getRoleName());

		userRole.setRole(role);

		return this.userRolerepository.save(userRole);
	}

}
