package com.example.curso.boot.demomvc.service;

import com.example.curso.boot.demomvc.dao.RoleDao;
import com.example.curso.boot.demomvc.dao.UserDao;
import com.example.curso.boot.demomvc.domain.Role;
import com.example.curso.boot.demomvc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Transactional
@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User buscarPorEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User buscarPorUsername(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User save(User user) {
        System.out.println(user.getPassword());


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role role = roleDao.findByRole("ADMIN");
        System.out.println(user.getPassword());
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));
        return userDao.save(user);
    }

}
