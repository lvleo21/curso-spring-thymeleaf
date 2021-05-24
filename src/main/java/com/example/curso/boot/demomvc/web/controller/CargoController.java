package com.example.curso.boot.demomvc.web.controller;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.domain.Departamento;
import com.example.curso.boot.demomvc.service.CargoService;
import com.example.curso.boot.demomvc.service.DepartamentoService;
import com.example.curso.boot.demomvc.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo){
        return "/cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("dir") Optional<String> dir){

        int paginaAtual = page.orElse(1);
        String direcao = dir.orElse("asc");

        PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(paginaAtual, direcao);

        model.addAttribute("pageCargo", pageCargo);
        return "/cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr){

        if(result.hasErrors()){
            return "/cargo/cadastro";
        }

        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
        return "redirect:/cargos/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model){

        Cargo cargo = cargoService.buscarPorId(id);
        model.addAttribute("cargo", cargo);
        return "cargo/cadastro";

    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result,RedirectAttributes attr){

        if(result.hasErrors()){
            return "/cargo/cadastro";
        }

        cargoService.editar(cargo);
        attr.addFlashAttribute("success", cargo.getNome() + " atualizado com sucesso.");
        return "redirect:/cargos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){

        if(cargoService.cargoTemFuncionarios(id)){
            attr.addFlashAttribute("erro", "Erro ao excluir o cargo. O mesmo, possuí funcionário(s) vinculado(s).");
        }else{
            cargoService.excluir(id);
            attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
        }

        return "redirect:/cargos/listar";
    }


    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos(){
        return departamentoService.buscarTodos();
    }
}
