package com.example.curso.boot.demomvc.web.validator;

import com.example.curso.boot.demomvc.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> c) {
        return User.class.equals(c);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;
        String password = user.getPassword();


        if (!(password.length() >= 6 && password.length() <= 11)) {
            errors.rejectValue("password", "User.length.error");
        }

        // Ao menos um número
        if(password.matches("^[^\\d]+$")){
            errors.rejectValue("password", "User.regex.number.error");
        }

        // Ao menos uma letra maiúsucla
        if(password.matches("^[^A-Z]+$")){
            errors.rejectValue("password", "User.regex.uppercase.error");
        }
        // Ao menos uma letra minúscula
        if(password.matches("^[^a-z]+$")){
            errors.rejectValue("password", "User.regex.lowercase.error");
        }

        // não pode conter caracteres especiais
        if(!password.matches("^[^\\W]*$")){
            errors.rejectValue("password", "User.regex.special.error");
        }

    }
}
