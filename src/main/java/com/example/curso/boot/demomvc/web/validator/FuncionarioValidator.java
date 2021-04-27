package com.example.curso.boot.demomvc.web.validator;

import com.example.curso.boot.demomvc.domain.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator {
    @Override
    public boolean supports(Class<?> c) { // validar o objeto que você está enviado pelo formulário
        return Funcionario.class.equals(c);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Funcionario funcionario = (Funcionario) object;
        LocalDate entrada = funcionario.getDataEntrada();


        if(funcionario.getDataSaida() != null){
            // isBefero = verifica se vem antes ou depois
            if(funcionario.getDataSaida().isBefore(entrada)){
                errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
            }
        }
    }
}
