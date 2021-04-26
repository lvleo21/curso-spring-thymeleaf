package com.example.curso.boot.demomvc.dao;

import com.example.curso.boot.demomvc.domain.Funcionario;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {
    @Override
    public List<Funcionario> findByName(String nome) {
        return  createQuery("select f from Funcionario f where f.nome like concat('%',?1, '%')", nome);
    }

    @Override
    public List<Funcionario> findByCargoId(Long id) {
        return createQuery("select f from Funcionario f where f.cargo.id = ?1 ", id);
    }

    @Override
    public List<Funcionario> findByDateRange(LocalDate entrada, LocalDate saida) {
        String jpql = new StringBuilder("select f from Funcionario f")
                .append("where f.dataEntrada >= ?1 and f.dataSaida <= ?2")
                .append("order by f.dataEntrada asc").toString();

        return createQuery(jpql, entrada, saida);
    }

    @Override
    public List<Funcionario> findByEntryDate(LocalDate entrada) {
        String jpql = new StringBuilder("select f from Funcionario f")
                .append("where f.dataEntrada = ?1 ")
                .append("order by f.dataEntrada asc").toString();

        return createQuery(jpql, entrada);
    }

    @Override
    public List<Funcionario> findByDepartureDate(LocalDate saida) {
        String jpql = new StringBuilder("select f from Funcionario f")
                .append("where f.dataSaida = ?1 ")
                .append("order by f.dataSaida asc").toString();

        return createQuery(jpql, saida);
    }
}
