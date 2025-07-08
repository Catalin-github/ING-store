package org.example.ingstore.config;


import org.example.ingstore.entity.Role;
import org.example.ingstore.entity.User;
import org.example.ingstore.repository.RoleRepository;
import org.example.ingstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.example.ingstore.utils.AppConstants.*;


@Configuration
public class BootstrapConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    public BootstrapConfig(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ApplicationRunner initializeUsersAndRoles() {
        return args -> {
            // Create roles if they don't exist
            if (roleRepository.findByName(ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(ROLE_ADMIN));
            }

            if (roleRepository.findByName(ROLE_USER).isEmpty()) {
                roleRepository.save(new Role(ROLE_USER));
            }

            // Create an admin user if none exist
            if (userRepository.findByUsername(ADMIN_USERNAME).isEmpty()) {
                User admin = new User();
                admin.setUsername(ADMIN_USERNAME);
                admin.setEmail(ADMIN_EMAIL);
                admin.setPassword(passwordEncoder.encode(adminPassword));
                admin.setRoles(Set.of(roleRepository.findByName(ROLE_ADMIN).get()));
                userRepository.save(admin);
            }
        };
    }
}