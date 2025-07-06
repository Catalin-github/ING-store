package org.example.ingstore.controller;

import org.example.ingstore.dto.ApiResponseDTO;
import org.example.ingstore.dto.RoleDTO;
import org.example.ingstore.dto.RoleRequestDTO;
import org.example.ingstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.ingstore.utils.AppConstants.*;


@RestController
@RequestMapping(ROLE_BASE_PATH)
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<RoleDTO>> createRole(@RequestBody RoleRequestDTO roleDTO) {
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(createdRole, ROLE_CREATE_SUCCESS), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<RoleDTO>>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return new ResponseEntity<>(new ApiResponseDTO<>(roles, ROLE_FETCH_ALL_SUCCESS), HttpStatus.OK);
    }

    @GetMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<RoleDTO>> getRoleById(@PathVariable Long id) {
        RoleDTO role = roleService.getRoleById(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(role, ROLE_FETCH_SUCCESS), HttpStatus.OK);
    }

    @DeleteMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<Void>> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(null, ROLE_DELETE_SUCCESS), HttpStatus.NO_CONTENT);
    }

    @PutMapping(GET_BY_ID_PATH)
    public ResponseEntity<ApiResponseDTO<RoleDTO>> updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);
        return new ResponseEntity<>(new ApiResponseDTO<>(updatedRole, ROLE_UPDATE_SUCCESS), HttpStatus.OK);
    }
}

