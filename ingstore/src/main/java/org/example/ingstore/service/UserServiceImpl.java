package org.example.ingstore.service;


import org.example.ingstore.dto.UserDTO;
import org.example.ingstore.entity.Role;
import org.example.ingstore.entity.User;
import org.example.ingstore.exception.ResourceNotFoundException;
import org.example.ingstore.repository.RoleRepository;
import org.example.ingstore.repository.UserRepository;
import org.example.ingstore.utils.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static org.example.ingstore.utils.AppConstants.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_USERNAME, username)));

        // Convert the user's roles to GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO, String plainPassword) {
        logger.info(USER_CREATE_LOG, userDTO.getUsername());

        String encodedPassword = passwordEncoder.encode(plainPassword);

        // Convert DTO to User entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPassword);

        // Fetch and assign roles
        Set<Role> roles = userDTO.getRoleNames() != null ? userDTO.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(String.valueOf(roleName))
                        .orElseThrow(() -> new ResourceNotFoundException(String.format(ROLE_NOT_FOUND, roleName))))
                .collect(Collectors.toSet()) : Collections.emptySet();

        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        logger.info(USER_CREATED_WITH_ID, savedUser.getId());

        return UserConverter.convertToDTO(savedUser);
    }

    @Override
    public UserDTO findUserById(Long id) {
        logger.info(ATTEMPT_FIND_USER_BY_ID, id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error(USER_NOT_FOUND_ID_LOG, id);
                    return new ResourceNotFoundException(String.format(USER_NOT_FOUND_ID, id));
                });

        logger.info(USER_FOUND_LOG, user.getUsername());
        return UserConverter.convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        logger.info(DELETING_USER_WITH_ID, id);
        if (!userRepository.existsById(id)) {
            logger.error(USER_NOT_FOUND_BY_ID, id);
            throw new ResourceNotFoundException(String.format(USER_NOT_FOUND_ID, id));
        }
        userRepository.deleteById(id);
        logger.info(USER_DELETED_WITH_ID, id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        logger.info(UPDATING_USER_WITH_ID, id);

        // Find the user by ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_ID, id)));

        // Fetch roles from the repository based on the role names provided in the DTO
        Set<Role> roles = userDTO.getRoleNames() != null ? userDTO.getRoleNames().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new ResourceNotFoundException(String.format(ROLE_NOT_FOUND, roleName))))
                .collect(Collectors.toSet()) : user.getRoles();

        // Convert DTO to User entity
        user = UserConverter.convertToEntity(userDTO, user.getPassword(), roles);
        user.setId(id);

        User updatedUser = userRepository.save(user);
        logger.info(USER_UPDATED_WITH_ID, id);

        return UserConverter.convertToDTO(updatedUser);
    }
}
