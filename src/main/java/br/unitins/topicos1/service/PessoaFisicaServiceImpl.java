package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.PessoaFisicaDTO;
import br.unitins.topicos1.dto.PessoaFisicaResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.model.PessoaFisica;
import br.unitins.topicos1.model.Sexo;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.repository.PessoaFisicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    @Inject
    public PessoaFisicaRepository pessoafisicaRepository;

    @Override
    @Transactional
    public PessoaFisicaResponseDTO create(@Valid PessoaFisicaDTO dto) {
        PessoaFisica pessoafisica = new PessoaFisica();
        pessoafisica.setNome(dto.nome());
        pessoafisica.setSexo(Sexo.valueOf(dto.idSexo()));
        pessoafisica.setCpf(dto.cpf());
        pessoafisica.setListaTelefone(new ArrayList<Telefone>());

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            pessoafisica.getListaTelefone().add(t);
        }

        pessoafisicaRepository.persist(pessoafisica);
        return PessoaFisicaResponseDTO.valueOf(pessoafisica);
    }

    @Override
    @Transactional
    public void update(Long id, PessoaFisicaDTO dto) {
        PessoaFisica pessoafisicaBanco =  pessoafisicaRepository.findById(id);

        pessoafisicaBanco.setNome(dto.nome());
        pessoafisicaBanco.setSexo(Sexo.valueOf(dto.idSexo()));
        pessoafisicaBanco.setCpf(dto.cpf());
        // apagando os telefones antigos
        pessoafisicaBanco.getListaTelefone().clear();

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            pessoafisicaBanco.getListaTelefone().add(t);
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        pessoafisicaRepository.deleteById(id);
    }

    @Override
    public PessoaFisicaResponseDTO findById(Long id) {
        return PessoaFisicaResponseDTO.valueOf(pessoafisicaRepository.findById(id));
    }

    @Override
    public List<PessoaFisicaResponseDTO> findAll() {
        return pessoafisicaRepository
        .listAll()
        .stream()
        .map(e -> PessoaFisicaResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PessoaFisicaResponseDTO> findByNome(String nome) {
        return pessoafisicaRepository.findByNome(nome).stream()
        .map(e -> PessoaFisicaResponseDTO.valueOf(e)).toList();
    }

}
