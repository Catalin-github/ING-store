package org.example.ingstore.repository;

import org.example.ingstore.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Test save user")
    public void testSaveUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testemail@gmail.com");
        user.setPassword("testpassword");

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
    }

    @Test
    @DisplayName("Test find user by username")
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testemail@gmail.com");
        user.setPassword("testpassword");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("testuser");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    @DisplayName("Test delete user")
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testemail@gmail.com");
        user.setPassword("testpassword");
        userRepository.save(user);

        userRepository.delete(user);

        Optional<User> deletedUser = userRepository.findById(user.getId());

        assertThat(deletedUser).isEmpty();
    }
}