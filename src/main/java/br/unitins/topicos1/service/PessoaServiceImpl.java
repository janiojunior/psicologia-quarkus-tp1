package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PessoaDTO;
import br.unitins.topicos1.dto.PessoaResponseDTO;
import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.model.Sexo;
import br.unitins.topicos1.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PessoaServiceImpl implements PessoaService {

    @Inject
    public PessoaRepository pessoaRepository;

    @Override
    @Transactional
    public PessoaResponseDTO create(@Valid PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setSexo(Sexo.valueOf(dto.idSexo()));

        pessoaRepository.persist(pessoa);
        return PessoaResponseDTO.valueOf(pessoa);
    }

    @Override
    @Transactional
    public void update(Long id, PessoaDTO dto) {
        Pessoa pessoaBanco =  pessoaRepository.findById(id);

        pessoaBanco.setNome(dto.nome());
        pessoaBanco.setSexo(Sexo.valueOf(dto.idSexo()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public PessoaResponseDTO findById(Long id) {
        return PessoaResponseDTO.valueOf(pessoaRepository.findById(id));
    }

    @Override
    public List<PessoaResponseDTO> findAll() {
        return pessoaRepository
        .listAll()
        .stream()
        .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PessoaResponseDTO> findByNome(String nome) {
        return pessoaRepository.findByNome(nome).stream()
        .map(e -> PessoaResponseDTO.valueOf(e)).toList();
    }

}
