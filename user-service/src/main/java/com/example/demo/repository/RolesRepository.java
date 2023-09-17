package com.example.demo.repository;

import com.example.demo.model.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // Optional but recommended for code clarity
public interface RolesRepository extends MongoRepository<Roles, String> {

    @Query("{role_name:?0}")
    Roles findByName(String roleName);
}
