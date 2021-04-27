package com.example.curso.boot.demomvc.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario extends AbstractEntity<Long>{

    @NotBlank
    @Size(max = 255, min = 3)
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    private BigDecimal salario;

    @NotNull
    @PastOrPresent(message="{PastOfPresent.funcionario.dataEntrada}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name ="data_entrada", nullable = false, columnDefinition = "DATE")
    private LocalDate dataEntrada;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name ="data_saida", columnDefinition = "DATE")
    private LocalDate dataSaida;

    @Valid
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @NotNull(message="{NotNull.funcionario.cargo}")
    @ManyToOne // um cargo vai ter muitos funcionários
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
