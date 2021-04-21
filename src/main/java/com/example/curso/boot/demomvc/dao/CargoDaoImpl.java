package com.example.curso.boot.demomvc.dao;

import com.example.curso.boot.demomvc.domain.Cargo;
import org.springframework.stereotype.Repository;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao{
}
