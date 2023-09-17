package com.example.demo.service;

import com.example.demo.request.UsersRequest;
import com.example.demo.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UsersService {

    ResponseEntity<ApiResponse> createUser(UsersRequest usersRequest);

}
