package br.unitins.topicos1.dto;

import java.util.List;

public record PessoaDTO (
    String nome,
    Integer idSexo,
    List<TelefoneDTO> telefones
) { }
