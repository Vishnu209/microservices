package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Optional but recommended for code clarity
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{user_email:?0}")
    Optional<User> findByEmail(String email);
}
