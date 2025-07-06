package org.example.ingstore.service;

import org.example.ingstore.dto.RoleDTO;
import org.example.ingstore.dto.RoleRequestDTO;

import java.util.List;


public interface RoleService {

    RoleDTO createRole(RoleRequestDTO roleDTO);

    List<RoleDTO> getAllRoles();

    RoleDTO getRoleById(Long id);

    void deleteRole(Long id);

    RoleDTO updateRole(Long id, RoleRequestDTO roleDTO);
}
