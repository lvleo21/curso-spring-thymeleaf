package com.example.curso.boot.demomvc.controller;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.Funcionario;
import com.example.curso.boot.demomvc.domain.UF;
import com.example.curso.boot.demomvc.service.CargoService;
import com.example.curso.boot.demomvc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
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
    public String listar(ModelMap model){
        List<Funcionario> funcionarios = funcionarioService.buscarTodos();
        model.addAttribute("funcionarios", funcionarios);
        return "funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr){

        if(result.hasErrors()){
            return "funcionario/cadastro";
        }

        funcionarioService.salvar(funcionario);

        attr.addFlashAttribute("success", "Funcionário inserido com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        model.addAttribute("funcionario", funcionario);
        return "funcionario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr){

        if(result.hasErrors()){
            return "funcionario/cadastro";
        }

        System.out.println("Entrou no editar");
        funcionarioService.editar(funcionario);
        attr.addFlashAttribute("success", "Funcionário editado com sucesso");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        funcionarioService.excluir(id);
        attr.addFlashAttribute("success", "Funcionário removido com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/buscar/nome/")
    public String buscarPorNome(@RequestParam("nome") String nome, ModelMap model ){
        List<Funcionario> funcionarios = funcionarioService.buscarPorNome(nome);
        model.addAttribute("funcionarios", funcionarios);
        return "funcionario/lista";
    }

    @GetMapping("/buscar/cargo/")
    public String buscarPorCargo(@RequestParam("id") Long id, ModelMap model ){
        List<Funcionario> funcionarios = funcionarioService.buscarPorCargo(id);
        model.addAttribute("funcionarios", funcionarios);
        return "funcionario/lista";
    }

    @GetMapping("/buscar/data/")
    public String buscarporData(@RequestParam(value = "entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                                @RequestParam(value = "saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
                                ModelMap model ){

        List<Funcionario> funcionarios = funcionarioService.buscarPorDatas(entrada, saida);
        model.addAttribute("funcionarios", funcionarios);
        return "funcionario/lista";
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
