package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import jakarta.validation.Valid;

public interface PedidoService {

    public PedidoResponseDTO create(@Valid PedidoDTO dto);
    public PedidoResponseDTO findById(Long id);
    public List<PedidoResponseDTO> findAll();
    public List<PedidoResponseDTO> findByPaciente(Long idPaciente);
    
}
