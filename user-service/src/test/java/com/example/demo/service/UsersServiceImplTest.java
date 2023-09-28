package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.ApiResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.serviceImpl.UsersServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {

	@InjectMocks
	private UsersServiceImpl userService;

	@Mock
	private UserRepository usersRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private RoleRepository rolesRepository;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtTokenProvider tokenProvider;

	public static final String USER_ROLE = "ROLE_USER";

	@Test
	@DisplayName("Junit test for createUser method")
	public void createUser_willReturnSuccess() {
		User user = User.builder().id("1").firstName("Jane").lastName("R").userName("jane111").email("test@gmail.com")
				.password("12345").build();
		user.setCreatedBy("Jane R");
		user.setCreatedDate(Instant.now());
		Role role = new Role();
		role.setRoleName(USER_ROLE);
		Set<Role> roles = new HashSet<Role>();
		user.setUserRoles(roles);

		given(usersRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
		given(passwordEncoder.encode(user.getPassword())).willReturn("encoded-password");
		given(usersRepository.save(user)).willReturn(user);

		System.out.println(usersRepository);
		System.out.println(userService);

		// Perform the createUser operation
		ResponseEntity<ApiResponse> userCreated = userService.createUser(user);
		System.out.println(userCreated);
		System.out.println(userCreated.getBody());
		// Verify that userRepository.save was called with the expected user
		verify(usersRepository).save(user);
		assertThat(userCreated.getBody().getData()).isNotNull();
		System.out.println(user.getPassword());

		assertEquals("encoded-password", user.getPassword());
		assertEquals("Jane", user.getFirstName());
		assertEquals("R", user.getLastName());
		assertEquals("jane111", user.getUserName());
		assertEquals("Jane R", user.getCreatedBy());
		assertEquals("test@gmail.com", user.getEmail());
		assertEquals("1", user.getId());

	}

}