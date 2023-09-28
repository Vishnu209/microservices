package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Optional but recommended for code clarity
public interface RoleRepository extends MongoRepository<Role, String> {

    @Query("{role_name:?0}")
    Optional<Role> findByName(String roleName);
}
