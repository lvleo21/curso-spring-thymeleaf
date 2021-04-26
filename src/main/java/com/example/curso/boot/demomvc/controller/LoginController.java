package com.example.curso.boot.demomvc.controller;

import com.example.curso.boot.demomvc.domain.User;
import com.example.curso.boot.demomvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/login");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("account/registration");
        return modelAndView;
    }

    @PostMapping("/registration/save")
    public String createNewUser(User user, BindingResult bindingResult, RedirectAttributes attr) {
        User userExists = userService.buscarPorUsername(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/registration";

        } else {
            userService.save(user);
            attr.addFlashAttribute("successMessage", "User has been registered successfully");
            attr.addFlashAttribute("user", new User());
            return "redirect:/login";
        }

    }
}
