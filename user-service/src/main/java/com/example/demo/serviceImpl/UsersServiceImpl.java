package com.example.demo.serviceImpl;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.response.JwtAuthenticationResponse;
import com.example.demo.response.ResponseData;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;

	public static final String USER_ROLE = "ROLE_USER";
	public static final String ADMIN_ROLE = "ROLE_ADMIN";

	@Override
	public ResponseEntity<ApiResponse> createUser(User user) {
		ApiResponse apiResponse = new ApiResponse();
		ResponseData data = new ResponseData();
		try {
			if (isEmailExists(user.getEmail())) {
				setApiResponse(false, null, "Email already exists", apiResponse, 400);
				return ResponseEntity.badRequest().body(apiResponse);
			}
			if (!isEmailValid(user.getEmail())) {
				setApiResponse(false, null, "Invalid Email provided", apiResponse, 400);
				return ResponseEntity.badRequest().body(apiResponse);
			}

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setCreatedDate(Instant.now());
			user.setCreatedBy(String.format("%s %s", user.getFirstName(), user.getLastName()));
			setRolesForUser(user, USER_ROLE);

			User userCreated = userRepository.save(user);
			data.setUser(userCreated);
			setApiResponse(true, data, "User created successfully", apiResponse, 201);
			return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			setApiResponse(true, data, "Failed to create User", apiResponse, 500);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}
	}

	private void setRolesForUser(User user, String userRole) {
		Role role = roleRepository.findByName(userRole).orElseGet(() -> {
			Role newRole = new Role();
			newRole.setRoleName(userRole);
			return roleRepository.save(newRole);
		});

		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setUserRoles(roles);
	}

	private boolean isEmailValid(String email) {
		EmailValidator emailValidator = EmailValidator.getInstance();
		return emailValidator.isValid(email);
	}

	private boolean isEmailExists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	public ApiResponse setApiResponse(boolean isSuccess, ResponseData data, String message, ApiResponse apiResponse,
			int code) {
		apiResponse.setSuccess(isSuccess);
		apiResponse.setData(data);
		apiResponse.setMessage(message);
		apiResponse.setCode(code);
		return apiResponse;
	}

	@Override
	public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest) {
		ApiResponse apiResponse = new ApiResponse();
		ResponseData responseData = new ResponseData();
		try {
			Optional<User> userExist = userRepository.findByEmail(loginRequest.getEmail());
			if (!userExist.isPresent()) {
				setApiResponse(false, null, "User doesnot exists in database!!", apiResponse, 400);
				return ResponseEntity.badRequest().body(apiResponse);
			}
			if (!passwordEncoder.matches(loginRequest.getPassword(), userExist.get().getPassword())) {
				setApiResponse(false, null, "Incorrect password provided!!", apiResponse, 400);
				return ResponseEntity.badRequest().body(apiResponse);
			}
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			if (authentication.isAuthenticated()) {
				JwtAuthenticationResponse jwt = tokenProvider.generateToken(loginRequest.getEmail());
				responseData.setJwtResponse(jwt);
				setApiResponse(true, responseData, "Logged in successfully", apiResponse, 200);
				return ResponseEntity.ok(apiResponse);
			} else {
				setApiResponse(true, responseData, "Logged in failed", apiResponse, 500);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			setApiResponse(true, responseData, "Logged in failed", apiResponse, 400);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
		}

	}
}
