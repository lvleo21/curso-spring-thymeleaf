package com.example.curso.boot.demomvc.dao;



import com.example.curso.boot.demomvc.domain.Funcionario;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioDao {

    void save(Funcionario funcionario);
    void update(Funcionario funcionario);
    void delete(Long id);
    Funcionario findById(Long id);
    List<Funcionario> findAll();
    List<Funcionario> findByName(String nome);
    List<Funcionario> findByCargoId(Long id);

    List<Funcionario> findByDateRange(LocalDate entrada, LocalDate saida);
    List<Funcionario> findByEntryDate(LocalDate entrada);
    List<Funcionario> findByDepartureDate(LocalDate saida);
}
