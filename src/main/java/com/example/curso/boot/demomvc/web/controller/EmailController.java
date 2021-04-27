package com.example.curso.boot.demomvc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.MimeMessage;


@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/send")
    public String sendMail() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo("mequeirozs@gmail.com");
            helper.setSubject("Teste Envio de e-mail");
            helper.setText("<h1>PROJETO PBD - TRANSPORTE !</h1> <p>Usuário: <b>_lvleo21</b></p> <p>Nova senha: 12312312312asdasd123123</p> <h2>Não responda este email.</h2> ", true);
            mailSender.send(mail);

            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }
}
