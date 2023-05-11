package com.example.Words.repositories;

import com.example.Words.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findFirstByName(String name);
}
