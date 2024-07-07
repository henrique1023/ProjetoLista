package br.com.projeto.controllers;

import br.com.projeto.model.ListaReproducao;
import br.com.projeto.services.ListaReproducaoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Lista Reproducao Endpoint")
@RequestMapping("api/lists/v1")
public class ListaReproducaoController {

    @Autowired
    ListaReproducaoServices services;

    @GetMapping
    public ResponseEntity<List<ListaReproducao>> listaReproducao() {
        return services.findAllListaReproducao();
    }

    @GetMapping(path = "/{listName}")
    public ResponseEntity<ListaReproducao> listaReproducao(@PathVariable String listName) {
        return services.findListByName(listName);
    }
}
