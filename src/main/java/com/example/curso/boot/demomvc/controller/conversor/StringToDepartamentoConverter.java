package com.example.curso.boot.demomvc.controller.conversor;

import com.example.curso.boot.demomvc.domain.Departamento;
import com.example.curso.boot.demomvc.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoService departamentoService;

    public Departamento convert(String text) {
        if (!text.isEmpty()){
            Long id = Long.valueOf(text);
            return departamentoService.buscarPorId(id);
        }
        return null;
    }
}
