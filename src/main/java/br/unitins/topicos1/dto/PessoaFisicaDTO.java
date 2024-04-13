package br.unitins.topicos1.dto;

import java.util.List;

public record PessoaFisicaDTO (
    String nome,
    Integer idSexo,
    String cpf,
    List<TelefoneDTO> telefones
) { }
