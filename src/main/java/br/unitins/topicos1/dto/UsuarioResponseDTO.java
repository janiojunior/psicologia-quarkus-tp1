package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.PessoaFisica;

public record UsuarioResponseDTO(
    String username,
    String nome
) {
    public static UsuarioResponseDTO valueOf(PessoaFisica pf) {
        return new UsuarioResponseDTO(
                pf.getUsuario().getUsername(),
                pf.getNome()
            );
    }
    
}
