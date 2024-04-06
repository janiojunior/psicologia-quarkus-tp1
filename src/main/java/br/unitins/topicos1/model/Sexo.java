package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
    FEMININO(1, "Feminino"),
    MASCULINO(2, "Masculino");

    private int id;
    private String nome;

    Sexo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static Sexo valueOf(Integer id) throws IllegalArgumentException {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.id == id)
                return sexo;
        }
        throw new IllegalArgumentException("id sexo inválido.");
    }

}
