package com.example.demo.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.utils.AuditUtils;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User extends AuditUtils {

	@Id
	private String id;

	@Field(name = "first_name")
	private String firstName;

	@Field(name = "last_name")
	private String lastName;

	@Field(name = "user_name")
	private String userName;

	@Field(name = "user_email")
	@Email(message = "Invalid email format")
	private String email;

	@Field(name = "user_password")
	private String password;

	@DBRef
	private Set<Role> userRoles;
}
