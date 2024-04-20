package br.unitins.topicos1.resource;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EstadoDTO;
import br.unitins.topicos1.dto.EstadoResponseDTO;
import br.unitins.topicos1.service.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class EstadoResourceTest {
 
    @Inject
    public EstadoService estadoService;

    @Test
    public void findAllTest() {
        given()
            .when()
            .get("/estados")
            .then()
            .statusCode(200)
            .body("nome", hasItem(is("Tocantins")));
    }

    @Test
    public void findByIdTest() {
        given()
            .when()
            .get("/estados/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
            .when()
            .get("/estados/search/nome/Tocantins")
            .then()
            .statusCode(200)
            .body("nome", everyItem(is("Tocantins")));
            //.body("nome", hasItem(is("Tocantins")));
            
    }

    @Test
    public void findBySiglaTest() {
        given()
            .when()
            .get("/estados/search/sigla/TO")
            .then()
            .statusCode(200)
            .body("sigla", everyItem(is("TO")));
            //.body("nome", hasItem(is("Tocantins")));
            
    }

    @Test
    public void createTest() {
        EstadoDTO dto = new EstadoDTO("Rio Grande do Sul", "TO");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .post("/estados")
            .then()
            .statusCode(201)
            .body("nome", is("Rio Grande do Sul"))
            .body("sigla", is("TO"));
    }

    @Test
    public void updateTest() {
        EstadoDTO dto = new EstadoDTO("test", "RS");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
            .when()
            .pathParam("id", 2)
            .put("/estados/{id}")
            .then()
            .statusCode(204);
  
    }

    @Test
    public void deleteTest() {
        EstadoResponseDTO response =  estadoService.create(new EstadoDTO("Para", "PA"));
        given()
            .when()
            .pathParam("id", response.id())
            .delete("/estados/{id}")
            .then()
            .statusCode(204);
  

        estadoService.delete(response.id());
        assertNull(estadoService.findById(response.id()));
    }
}
