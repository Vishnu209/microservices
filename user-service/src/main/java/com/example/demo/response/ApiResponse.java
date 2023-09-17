package com.example.demo.response;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean success;
    private int code;
    private ResponseData data;
    private String message;
}
