package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.EstadoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoRepository estadoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> findAll() {
        return estadoRepository.listAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void create(Estado estado) {
        estadoRepository.persist(estado);
    }
}
