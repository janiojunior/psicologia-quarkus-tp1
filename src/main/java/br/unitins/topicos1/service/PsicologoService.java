package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PsicologoDTO;
import br.unitins.topicos1.dto.PsicologoResponseDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface PsicologoService {

    public PsicologoResponseDTO create(@Valid PsicologoDTO dto);

    public void update(Long id, PsicologoDTO dto);

    public void delete(Long id);

    public PsicologoResponseDTO findById(Long id);

    public List<PsicologoResponseDTO> findAll();

    public List<PsicologoResponseDTO> findByNome(String nome);

    public UsuarioResponseDTO login(String username, String senha);
}
