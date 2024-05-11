package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.PsicologoDTO;
import br.unitins.topicos1.dto.PsicologoResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.PessoaFisica;
import br.unitins.topicos1.model.Psicologo;
import br.unitins.topicos1.model.Sexo;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.PessoaFisicaRepository;
import br.unitins.topicos1.repository.PsicologoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PsicologoServiceImpl implements PsicologoService {

    @Inject
    public PsicologoRepository psicologoRepository;

    @Inject
    public PessoaFisicaRepository pessoaFisicaRepository;

    @Inject
    public UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Override
    @Transactional
    public PsicologoResponseDTO create(@Valid PsicologoDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        // gereando o hash da senha
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        // salvando o usuario
        usuarioRepository.persist(usuario);
      
        PessoaFisica pessoafisica = new PessoaFisica();
        pessoafisica.setNome(dto.nome());
        pessoafisica.setSexo(Sexo.valueOf(dto.idSexo()));
        pessoafisica.setCpf(dto.cpf());
        pessoafisica.setListaTelefone(new ArrayList<Telefone>());
        pessoafisica.setUsuario(usuario);

        for (TelefoneDTO tel : dto.telefones()) {
            Telefone t = new Telefone();
            t.setCodigoArea(tel.codigoArea());
            t.setNumero(tel.numero());
            pessoafisica.getListaTelefone().add(t);
        }
        // salvando pessoa fisica
        pessoaFisicaRepository.persist(pessoafisica);

        Psicologo psicologo = new Psicologo();
        psicologo.setCrp(dto.crp());
        psicologo.setPessoaFisica(pessoafisica);

        // salvando o psicologo
        psicologoRepository.persist(psicologo);

        return PsicologoResponseDTO.valueOf(psicologo);
    }

    @Override
    @Transactional
    public void update(Long id, PsicologoDTO dto) {
        // Psicologo psicologoBanco =  psicologoRepository.findById(id);

        // psicologoBanco.setNome(dto.nome());
        // psicologoBanco.setSexo(Sexo.valueOf(dto.idSexo()));
        // psicologoBanco.setCpf(dto.cpf());
        // // apagando os telefones antigos
        // psicologoBanco.getListaTelefone().clear();

        // for (TelefoneDTO tel : dto.telefones()) {
        //     Telefone t = new Telefone();
        //     t.setCodigoArea(tel.codigoArea());
        //     t.setNumero(tel.numero());
        //     psicologoBanco.getListaTelefone().add(t);
        // }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        psicologoRepository.deleteById(id);
    }

    @Override
    public PsicologoResponseDTO findById(Long id) {
        return PsicologoResponseDTO.valueOf(psicologoRepository.findById(id));
    }

    @Override
    public List<PsicologoResponseDTO> findAll() {
        return psicologoRepository
        .listAll()
        .stream()
        .map(e -> PsicologoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PsicologoResponseDTO> findByNome(String nome) {
        return psicologoRepository.findByNome(nome).stream()
        .map(e -> PsicologoResponseDTO.valueOf(e)).toList();
    }

    public UsuarioResponseDTO login(String username, String senha) {
        Psicologo psicologo = psicologoRepository.findByUsernameAndSenha(username, senha);
        return UsuarioResponseDTO.valueOf(psicologo.getPessoaFisica());
    }

}
