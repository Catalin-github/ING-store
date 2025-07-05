package org.example.ingstore.repository;

import org.example.ingstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username for authentication purposes
    Optional<User> findByUsername(String username);

}