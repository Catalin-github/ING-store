package org.example.ingstore.utils;

import org.example.ingstore.dto.RoleDTO;
import org.example.ingstore.dto.RoleRequestDTO;
import org.example.ingstore.entity.Role;

public class RoleConverter {

    // Convert Role Entity to RoleDTO
    public static RoleDTO convertToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    // Convert RoleDTO to Role Entity
    public static Role convertToEntity(RoleRequestDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }
}