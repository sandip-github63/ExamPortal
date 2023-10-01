package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.request.AuthRequest;
import com.exam.response.Message;
import com.exam.service.JwtService;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private final UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	UserService service;

	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		Role r1 = new Role();
		r1.setRoleName("ROLE_ADMIN");
		r1.setRoleId(45L);

		Set<UserRole> userRoleSet = new HashSet<>();

		UserRole userRole = new UserRole();
		userRole.setRole(r1);
		userRole.setUser(user);

		userRoleSet.add(userRole);

		// service call
		User u = service.createUser(user, userRoleSet);

		if (u == null) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("user already exit!!!!", "409", u));

		}

		return ResponseEntity.status(HttpStatus.OK).body(new Message("user created successfully", "200", u));
	}

	@GetMapping("/{username}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") // role base authorization
	public ResponseEntity<?> getUser(@PathVariable("username") String username) {

		User user = service.getUserByUserName(username);

		if (user == null) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("invalid username!!!!", "404", user));

		}

		return ResponseEntity.status(HttpStatus.OK).body(new Message("user get successfully", "200", user));

	}

	@DeleteMapping("/{userid}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") // role base authorization
	public ResponseEntity<?> deleteUser(@PathVariable("userid") Long userid) {

		boolean result = service.deleteUser(userid);

		if (!result) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("invalid user id!!!!", "404"));

		}

		return ResponseEntity.status(HttpStatus.OK).body(new Message("user deleted successfully", "200"));

	}

//this handler is responsible to take username and password from client and generate token

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest request) {

		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		// authenticationManger send UsernamePasswordToken object to ProviderManger and
		// providerManager check with AuthenticationProvider and AuthenticationProvider
		// check the usercrediation by comparing actual and database by calling
		// UserDetailsService
		// and if authentication success or failure then it return Authentication object

		if (authenticate.isAuthenticated()) {

			return jwtService.generateToken(request.getUserName());

		} else {
			throw new UsernameNotFoundException("invalid username !!");
		}

	}

}
