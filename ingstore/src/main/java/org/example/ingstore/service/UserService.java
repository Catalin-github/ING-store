package org.example.ingstore.service;

import org.example.ingstore.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO, String password);

    UserDTO findUserById(Long id);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);
}

