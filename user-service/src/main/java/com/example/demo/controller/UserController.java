package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid User user) {
		return usersService.createUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
		return usersService.loginUser(loginRequest);
	}
}
