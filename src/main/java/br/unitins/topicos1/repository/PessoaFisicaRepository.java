package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {

    public List<PessoaFisica> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }

}
