package com.example.curso.boot.demomvc.service;

import com.example.curso.boot.demomvc.domain.User;

public interface UserService {
    User buscarPorEmail(String email);
    User buscarPorUsername(String userName);
    User save(User user);

}
