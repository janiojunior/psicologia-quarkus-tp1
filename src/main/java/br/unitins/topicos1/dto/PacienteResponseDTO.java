package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Paciente;

public record PacienteResponseDTO (
    Long id,
    String nome
) {
    public static PacienteResponseDTO valueOf(Paciente paciente) {
        return new PacienteResponseDTO(
            paciente.getId(), 
            paciente.getNome());
    }
    
}
