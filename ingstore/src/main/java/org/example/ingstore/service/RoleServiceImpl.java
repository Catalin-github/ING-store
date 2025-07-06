package org.example.ingstore.service;

import org.example.ingstore.dto.RoleDTO;
import org.example.ingstore.dto.RoleRequestDTO;
import org.example.ingstore.entity.Role;
import org.example.ingstore.exception.ResourceNotFoundException;
import org.example.ingstore.repository.RoleRepository;
import org.example.ingstore.utils.RoleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.ingstore.utils.AppConstants.*;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO createRole(RoleRequestDTO roleDTO) {
        logger.info(ROLE_CREATED_SUCCESS, roleDTO.getName());
        Role role = RoleConverter.convertToEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return RoleConverter.convertToDTO(savedRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ROLE_NOT_FOUND_WITH_ID + id));
        return RoleConverter.convertToDTO(role);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            logger.error(ROLE_NOT_FOUND_WITH_ID, id);
            throw new ResourceNotFoundException(ROLE_NOT_FOUND_WITH_ID + id);
        }
        roleRepository.deleteById(id);
        logger.info(ROLE_DELETED_SUCCESS, id);
    }

    @Override
    public RoleDTO updateRole(Long id, RoleRequestDTO roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ROLE_NOT_FOUND_WITH_ID + id));
        role.setName(roleDTO.getName());
        Role updatedRole = roleRepository.save(role);
        return RoleConverter.convertToDTO(updatedRole);
    }
}

