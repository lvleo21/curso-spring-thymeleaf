package com.example.curso.boot.demomvc.web.error;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class MyErrorView implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {

        //map.forEach((k,v) -> System.out.println(k + ":" + v + "\n"));

        ModelAndView model = new ModelAndView("/error");
        model.addObject("status", status.value());

        switch (status.value()){
            case 404:
                model.addObject("error", "Página não encontrada");
                model.addObject("message", "A url para a página '" + map.get("path") + "' não existe.");
                break;
            case 500:
                model.addObject("error", "Ocorreu um erro interno no servidor");
                model.addObject("message", "Ocorreu um erro inexperado, tente mais tarde.");
                break;
            default:
                model.addObject("error", map.get("error"));
                model.addObject("message", map.get("message"));
                break;

        }

        return model;


    }
}
