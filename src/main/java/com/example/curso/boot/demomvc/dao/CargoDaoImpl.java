package com.example.curso.boot.demomvc.dao;

import com.example.curso.boot.demomvc.domain.Cargo;
import com.example.curso.boot.demomvc.util.PaginacaoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{

    public PaginacaoUtil<Cargo> buscaPaginada (int pagina, String direcao){

        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho;


        List<Cargo> cargos = getEntityManager()
                .createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();


        Long totalRegistros = (this.count() + (tamanho-1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalRegistros,cargos, direcao);
    }

    public long count(){
        return getEntityManager().createQuery("select count(*) from Cargo", Long.class).getSingleResult();
    }

}
