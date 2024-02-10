package br.unitins.topicos1.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pessoa extends PanacheEntity {

    public String nome;
    public String cpf;
    
}
