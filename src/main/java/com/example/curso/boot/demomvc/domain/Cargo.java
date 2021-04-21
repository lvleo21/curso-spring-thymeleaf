package com.example.curso.boot.demomvc.domain;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long>{

    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @ManyToOne // um departamento poderá ter muitos cargos
    @JoinColumn(name = "departamento_id") // nome da chave estrangeira na tablea
    private Departamento departamento;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
