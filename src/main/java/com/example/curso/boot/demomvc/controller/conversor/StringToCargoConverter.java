package com.example.curso.boot.demomvc.controller.conversor;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

    @Autowired
    private CargoService cargoService;


    public Cargo convert(String text) {
        if(!text.isEmpty()){
            Long id = Long.valueOf(text);
            cargoService.buscarPorId(id);
        }

        return null;
    }
}
