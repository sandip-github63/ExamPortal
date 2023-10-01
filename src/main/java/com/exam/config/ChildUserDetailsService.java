package com.exam.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.exam.model.User;
import com.exam.repository.UserRepository;

@Component
public class ChildUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("inside loaduserbyusername");

		// fetch user by username from db
		User user = repo.findByUserName(username);

		System.out.println(" inside find by username");

		// user is User and method expected return type is UserDetails type hence we can
		// not return
		// so we need to convert user to UserDetails type
		// user all data set UserDetails's impl child.

		// constructor reference and user is passed as argument

		Optional<User> user1 = Optional.ofNullable(user);

		return user1.map(ChildUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found" + user));

	}

}
