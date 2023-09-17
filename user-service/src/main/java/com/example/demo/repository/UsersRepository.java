package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // Optional but recommended for code clarity
public interface UsersRepository extends MongoRepository<Users, String> {

    @Query("{user_email:?0}")
    Users findByEmail(String email);
}
