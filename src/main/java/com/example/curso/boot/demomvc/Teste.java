package com.example.curso.boot.demomvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Teste {
    public static void main(String[] args) {
        LocalDate start = LocalDate.now();
        LocalDate finish = LocalDate.now().plusDays(30);

        LocalDateTime start2 = LocalDateTime.now();
        LocalDateTime finish2 = LocalDateTime.now().plusDays(30);

        System.out.println(start);
        System.out.println(finish);

        System.out.println(start2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println(finish2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
