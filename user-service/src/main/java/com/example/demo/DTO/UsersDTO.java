package com.example.demo.DTO;

import com.example.demo.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private Set<Roles> userRoles;
}
