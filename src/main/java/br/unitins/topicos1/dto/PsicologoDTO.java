package br.unitins.topicos1.dto;

import java.util.List;

public record PsicologoDTO (
    String cpf,
    String nome,
    String crp,
    Integer idSexo,
    String username,
    String senha,
    List<TelefoneDTO> telefones) 
{ }

