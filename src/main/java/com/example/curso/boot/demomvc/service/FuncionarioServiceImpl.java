package com.example.curso.boot.demomvc.service;

import com.example.curso.boot.demomvc.dao.FuncionarioDao;
import com.example.curso.boot.demomvc.domain.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioDao dao;


    @Override
    public void salvar(Funcionario funcionario) {
        dao.save(funcionario);
    }

    @Override
    public void editar(Funcionario funcionario) {
        dao.update(funcionario);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Funcionario buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarPorNome(String nome) {
        return dao.findByName(nome);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarPorCargo(Long id) {
        if (id != null) {
            return dao.findByCargoId(id);
        }

        return  new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {

        if (entrada != null && saida != null) {
            return dao.findByDateRange(entrada, saida);
        } else if (entrada != null) {
            return dao.findByEntryDate(entrada);
        } else if (saida != null) {
            return dao.findByDepartureDate(saida);
        } else {
            return new ArrayList<>();
        }

    }
}
