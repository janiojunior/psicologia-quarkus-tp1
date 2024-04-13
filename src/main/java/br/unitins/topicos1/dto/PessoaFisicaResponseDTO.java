package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.model.PessoaFisica;
import br.unitins.topicos1.model.Sexo;

public record PessoaFisicaResponseDTO(
    Long id,
    String nome,
    Sexo sexo,
    String cpf,
    List<TelefoneResponseDTO> telefones
) {
    public static PessoaFisicaResponseDTO valueOf(PessoaFisica pessoaFisica) {
        List<TelefoneResponseDTO> lista = pessoaFisica.getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new PessoaFisicaResponseDTO(
            pessoaFisica.getId(), 
            pessoaFisica.getNome(), 
            pessoaFisica.getSexo(),
            pessoaFisica.getCpf(),
            lista);
    }
    
}
