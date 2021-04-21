package com.example.curso.boot.demomvc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamento  extends AbstractEntity<Long>{

    @Column(nullable = false, unique = true, length = 60)
    private String nome;
    // mappedBy é o nome do atributo relacionado ao departamento na classe Cargo
    @OneToMany(mappedBy = "departamento") // Muitos cargos para um departamento
    private List<Cargo> cargos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}
