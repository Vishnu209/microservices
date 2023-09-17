package com.example.demo.model;

import com.example.demo.utils.AuditUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class Users extends AuditUtils {

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
	private Set<Roles> userRoles;
}
