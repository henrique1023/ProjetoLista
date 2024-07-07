package br.com.projeto.controllers;

import br.com.projeto.model.ListaReproducao;
import br.com.projeto.services.ListaReproducaoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Lista Reproducao Endpoint")
@RequestMapping("/lists")
public class ListaReproducaoController {

    @Autowired
    ListaReproducaoServices services;

    @GetMapping
    public ResponseEntity<List<ListaReproducao>> listaReproducao() {
        return ResponseEntity.noContent().build();
    }
}
