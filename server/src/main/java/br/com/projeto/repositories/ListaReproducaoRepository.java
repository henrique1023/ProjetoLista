package br.com.projeto.repositories;

import br.com.projeto.model.ListaReproducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaReproducaoRepository extends JpaRepository<ListaReproducao, Long> {

   ListaReproducao findByNome(String listName);
}
