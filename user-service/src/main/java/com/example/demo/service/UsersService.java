package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsersService {

    ResponseEntity<ApiResponse> createUser(User user);

    ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest);
}
