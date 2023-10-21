package com.exam.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Role;
import com.exam.repository.RoleRepository;
import com.exam.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	public Role getRole(String roleName) {
		System.out.println("inside Role Service Impl method......");

		return this.repository.findByRoleName(roleName);
	}

}
