package br.com.projeto.controllers;

import br.com.projeto.data.vo.v1.ListaReproducaoVO;
import br.com.projeto.model.ListaReproducao;
import br.com.projeto.services.ListaReproducaoServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Lista Reproducao Endpoint")
@RequestMapping("api/lists/v1")
public class ListaReproducaoController {

    @Autowired
    ListaReproducaoServices services;

    @GetMapping
    public ResponseEntity<List<ListaReproducao>> findAllListaReproducao() {
        return services.findAllListaReproducao();
    }

    @GetMapping(path = "/{listName}")
    public ResponseEntity<ListaReproducao> findListaReproducaoByName(@PathVariable String listName) {
        return services.findListByName(listName);
    }

    @PostMapping
    public ResponseEntity<ListaReproducaoVO> saveListaReproducao(@RequestBody ListaReproducao listaReproducao) {
        return services.saveListaReproducao(listaReproducao);
    }

    public ListaReproducaoVO findById(Long key) {
        return services.findById(key);
    }
}
