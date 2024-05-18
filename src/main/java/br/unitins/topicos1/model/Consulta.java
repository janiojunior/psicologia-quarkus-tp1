package br.unitins.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class Consulta {
    private String nome;
    private Double preco;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
