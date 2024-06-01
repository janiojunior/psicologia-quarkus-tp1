package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.service.EstadoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoService estadoService;

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @GET

    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        LOG.info("Executando o buscarPeloId");
        LOG.infof("Executando o método buscarPeloId. Id: %s", id.toString());
        return Response.ok(estadoService.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Psicologo", "Paciente"})
    public Response findAll() {
        LOG.info("Executando o findAll");
        return Response.ok(estadoService.findAll()).build();
    }

    @GET
    @RolesAllowed("Paciente")
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(estadoService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/sigla/{sigla}")
    public Response findBySigla(@PathParam("sigla") String sigla) {
        return Response.ok(estadoService.findBySigla(sigla)).build();
    }

    @POST
    public Response create(EstadoDTO dto) {
        LOG.info("INFO");
        LOG.warn("WARN");
        LOG.error("ERROR");
        LOG.fatal("FATAL");
        LOG.trace("TRACE");
        LOG.debugf("DTO: %s", dto);
        return Response.status(Status.CREATED).entity(estadoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EstadoDTO dto) {
        estadoService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return Response.status(Status.NO_CONTENT).build();
            
    }


}
