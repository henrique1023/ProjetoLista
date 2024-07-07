package br.com.projeto.services;

import br.com.projeto.model.ListaReproducao;
import br.com.projeto.repositories.ListaReproducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaReproducaoServices {
    @Autowired
    private ListaReproducaoRepository repository;

    public ResponseEntity<List<ListaReproducao>> findAllListaReproducao() {
        List<ListaReproducao> listaReproducao = new ArrayList<>();
        listaReproducao = repository.findAll();
        return ResponseEntity.ok(listaReproducao);
    }

    public ResponseEntity<ListaReproducao> findListByName(String listName) {
        ListaReproducao listaReproducao = null;
        listaReproducao = repository.findByNome(listName);
        if (listaReproducao != null) {
            return ResponseEntity.ok(listaReproducao);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
