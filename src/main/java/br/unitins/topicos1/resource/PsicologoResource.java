package br.unitins.topicos1.resource;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.PsicologoDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.FileService;
import br.unitins.topicos1.service.PsicologoFileServiceImpl;
import br.unitins.topicos1.service.PsicologoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/psicologos")
public class PsicologoResource {

    @Inject
    public PsicologoService psicologoService;

    @Inject
    public PsicologoFileServiceImpl fileService;

    @GET

    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(psicologoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(psicologoService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(psicologoService.findByNome(nome)).build();
    }

    @POST
    public Response create(PsicologoDTO dto) {
        return Response.status(Status.CREATED).entity(psicologoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PsicologoDTO dto) {
        psicologoService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        psicologoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        fileService.salvar(id, form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }   

}
