package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Psicologo;
import br.unitins.topicos1.model.Sexo;

public record PsicologoResponseDTO(
    Long id,
    String cpf,
    String nome,
    String crp,
    Sexo sexo,
    String username,
    String senha,
    List<TelefoneResponseDTO> telefones
) {
    public static PsicologoResponseDTO valueOf(Psicologo psicologo) {
        List<TelefoneResponseDTO> lista = psicologo.getPessoaFisica().getListaTelefone()
                                            .stream()
                                            .map(TelefoneResponseDTO::valueOf)
                                            .toList();
        return new PsicologoResponseDTO(
            psicologo.getId(), 
            psicologo.getPessoaFisica().getCpf(),
            psicologo.getPessoaFisica().getNome(), 
            psicologo.getCrp(),
            psicologo.getPessoaFisica().getSexo(),
            psicologo.getPessoaFisica().getUsuario().getUsername(),
            psicologo.getPessoaFisica().getUsuario().getSenha(),
            lista);
    }
    
}
