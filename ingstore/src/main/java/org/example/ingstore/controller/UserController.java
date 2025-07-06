package org.example.ingstore.controller;


import org.example.ingstore.dto.ApiResponseDTO;
import org.example.ingstore.dto.UserDTO;
import org.example.ingstore.dto.UserRequestDTO;
import org.example.ingstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.example.ingstore.utils.AppConstants.*;

import java.util.List;

@RestController
@RequestMapping(USER_BASE_PATH)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping(CREATE_PATH)
    public ResponseEntity<ApiResponseDTO<UserDTO>> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserDTO createdUser = userService.createUser(
                new UserDTO(null, userRequestDTO.getUsername(), userRequestDTO.getEmail(), userRequestDTO.getRoleNames()),
                userRequestDTO.getPassword()
        );
        return new ResponseEntity<>(new ApiResponseDTO<>(createdUser, USER_CREATE_SUCCESS), HttpStatus.CREATED);
    }

    // Get user by ID
    @GetMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<UserDTO>> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.findUserById(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(userDTO, USER_FETCH_SUCCESS), HttpStatus.OK);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<UserDTO>>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(new ApiResponseDTO<>(users, USERS_FETCH_ALL_SUCCESS), HttpStatus.OK);
    }

    // Update user by ID
    @PutMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<UserDTO>> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(updatedUser, USER_UPDATE_SUCCESS), HttpStatus.OK);
    }

    // Delete user by ID
    @DeleteMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(null, USER_DELETE_SUCCESS), HttpStatus.NO_CONTENT);
    }
}
