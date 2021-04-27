package com.example.curso.boot.demomvc.web.controller;

import com.example.curso.boot.demomvc.domain.User;
import com.example.curso.boot.demomvc.service.UserService;
import com.example.curso.boot.demomvc.web.validator.FuncionarioValidator;
import com.example.curso.boot.demomvc.web.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new UserValidator());
    }

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
    public String createNewUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attr) {
        System.out.println("ENTROU NO CREATE NEW USER");

//        User userExists = userService.buscarPorUsername(user.getUserName());
//
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("userName", "error.user",
//                            "There is already a user registered with the user name provided");
//        }

        if (bindingResult.hasErrors()) {
            System.out.println("ENTROU NO HAS ERRORS DO BINDRESULT DO USUARIO");
            return "account/registration";

        } else {
            userService.save(user);
            attr.addFlashAttribute("successMessage", "User has been registered successfully");
            return "redirect:/login";
        }

    }
}
