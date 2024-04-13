package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PessoaFisicaDTO;
import br.unitins.topicos1.dto.PessoaFisicaResponseDTO;
import jakarta.validation.Valid;

public interface PessoaFisicaService {

    public PessoaFisicaResponseDTO create(@Valid PessoaFisicaDTO dto);

    public void update(Long id, PessoaFisicaDTO dto);

    public void delete(Long id);

    public PessoaFisicaResponseDTO findById(Long id);

    public List<PessoaFisicaResponseDTO> findAll();

    public List<PessoaFisicaResponseDTO> findByNome(String nome);
}
