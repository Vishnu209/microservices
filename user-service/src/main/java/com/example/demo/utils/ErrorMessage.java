package com.example.demo.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "error.message")
public class ErrorMessage {

    private String userCreation;
    private String emaiInvalid;
    private String emailNotFound;
}
