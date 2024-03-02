package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.model.Estado;
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
@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoRepository estadoRepository;

    @GET

    @Path("/{id}")
    public EstadoResponseDTO findById(@PathParam("id") Long id) {
        return EstadoResponseDTO.valueOf(estadoRepository.findById(id));
    }

    @GET
    public List<EstadoResponseDTO> findAll() {
        // List<Estado> lista = estadoRepository.listAll();
        // List<EstadoResponseDTO> listaDto = new ArrayList<EstadoResponseDTO>();
        // for (Estado estado : lista) {
        //     listaDto.add(EstadoResponseDTO.valueOf(estado));
        // }
        // return listaDto;

        return estadoRepository
            .listAll()
            .stream()
            .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<EstadoResponseDTO> findByNome(@PathParam("nome") String nome) {
        return estadoRepository.findByNome(nome).stream()
        .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @GET
    @Path("/search/sigla/{sigla}")
    public List<EstadoResponseDTO> findBySigla(@PathParam("sigla") String sigla) {
        return estadoRepository.findBySigla(sigla).stream()
        .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }


    @POST
    @Transactional
    public EstadoResponseDTO create(EstadoDTO dto) {
        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());

        estadoRepository.persist(estado);
        return EstadoResponseDTO.valueOf(estado);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public void update(@PathParam("id") Long id, EstadoDTO dto) {
        Estado estadoBanco =  estadoRepository.findById(id);

        estadoBanco.setNome(dto.nome());
        estadoBanco.setSigla(dto.sigla());
  
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        estadoRepository.deleteById(id);
    }


}
