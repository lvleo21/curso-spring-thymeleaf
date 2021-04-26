package com.example.curso.boot.demomvc.controller;

import com.example.curso.boot.demomvc.dao.RoleDao;
import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.User;
import com.example.curso.boot.demomvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleDao roleDao;

    @GetMapping("/")
    public String home(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.buscarPorUsername(auth.getName());
        System.out.println(user.getRoles());
        session.setAttribute("user", user);

        return "/home";
    }



}

