package org.example.ingstore.repository;


import org.example.ingstore.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("Test save role")
    public void testSaveRole() {
        Role role = new Role();
        role.setName("ROLE_USER");

        Role savedRole = roleRepository.save(role);

        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getName()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("Test find role by name")
    public void testFindByName() {
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);

        Optional<Role> foundRole = roleRepository.findByName("ROLE_USER");

        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("Test delete role")
    public void testDeleteRole() {
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);

        roleRepository.delete(role);

        Optional<Role> deletedRole = roleRepository.findById(role.getId());

        assertThat(deletedRole).isEmpty();
    }
}
