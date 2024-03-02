package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.CidadeDTO;
import br.unitins.topicos1.dto.CidadeResponseDTO;
import br.unitins.topicos1.model.Cidade;
import br.unitins.topicos1.repository.CidadeRepository;
import br.unitins.topicos1.repository.EstadoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cidades")
public class CidadeResource {
    
    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public EstadoRepository estadoRepository;

    @GET

    @Path("/{id}")
    public CidadeResponseDTO findById(@PathParam("id") Long id) {
        return CidadeResponseDTO.valueOf(cidadeRepository.findById(id));
    }

    @GET
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository
            .listAll()
            .stream()
            .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<CidadeResponseDTO> findByNome(@PathParam("nome") String nome) {
        return cidadeRepository.findByNome(nome).stream()
        .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }

    @POST
    @Transactional
    public CidadeResponseDTO create(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setNome(dto.nome());
        cidade.setEstado(estadoRepository.findById(dto.idEstado()));

        cidadeRepository.persist(cidade);
        return CidadeResponseDTO.valueOf(cidade);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public void update(@PathParam("id") Long id, CidadeDTO dto) {
        Cidade cidade =  cidadeRepository.findById(id);

        cidade.setNome(dto.nome());
        cidade.setEstado(estadoRepository.findById(dto.idEstado()));
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        cidadeRepository.deleteById(id);
    }

}
