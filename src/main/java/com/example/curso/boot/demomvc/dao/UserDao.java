package com.example.curso.boot.demomvc.dao;

import com.example.curso.boot.demomvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
