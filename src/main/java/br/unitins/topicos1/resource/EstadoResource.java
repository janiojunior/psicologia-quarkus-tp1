package br.unitins.topicos1.resource;

import java.util.List;

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
    public Estado findById(@PathParam("id") Long id) {
        return estadoRepository.findById(id);
    }

    @GET
    public List<Estado> findAll() {
        return estadoRepository.listAll();
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<Estado> findByNome(@PathParam("nome") String nome) {
        return estadoRepository.findByNome(nome);
    }

    @GET
    @Path("/search/sigla/{sigla}")
    public List<Estado> findBySigla(@PathParam("sigla") String sigla) {
        
        return estadoRepository.findBySigla(sigla);
    }


    @POST
    @Transactional
    public Estado create(Estado estado) {
        estadoRepository.persist(estado);
        return estado;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public void update(@PathParam("id") Long id, Estado estado) {
        Estado estadoBanco =  estadoRepository.findById(id);

        estadoBanco.setNome(estado.getNome());
        estadoBanco.setSigla(estado.getSigla());
  
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        estadoRepository.deleteById(id);
    }


}
