package com.example.curso.boot.demomvc.dao;

import com.example.curso.boot.demomvc.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
