package org.example.ingstore.utils;



import org.example.ingstore.dto.UserDTO;
import org.example.ingstore.entity.Role;
import org.example.ingstore.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {

    // Convert User Entity to UserDTO
    public static UserDTO convertToDTO(User user) {
        Set<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), roleNames);
    }

    // Convert UserDTO to User Entity
    public static User convertToEntity(UserDTO userDTO, String password, Set<Role> roles) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(password);
        user.setEmail(userDTO.getEmail());
        user.setRoles(roles);
        return user;
    }
}
