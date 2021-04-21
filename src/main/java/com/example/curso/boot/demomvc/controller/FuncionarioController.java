package com.example.curso.boot.demomvc.controller;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.Funcionario;
import com.example.curso.boot.demomvc.domain.UF;
import com.example.curso.boot.demomvc.service.CargoService;
import com.example.curso.boot.demomvc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;



    @GetMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario){
        return "/funcionario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(){
        return "/funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario, RedirectAttributes attr){
        funcionarioService.salvar(funcionario);
        attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @ModelAttribute("cargos")
    public List<Cargo> getCargos(){
        return cargoService.buscarTodos();
    }

    @ModelAttribute("ufs")
    public UF[] getUFs(){
        return UF.values();
    }

}
