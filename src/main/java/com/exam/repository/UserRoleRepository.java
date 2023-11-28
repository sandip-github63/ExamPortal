package com.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.model.User;
import com.exam.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	public List<UserRole> findByUser(User user);

}
