package br.com.projeto.services;

import br.com.projeto.controllers.ListaReproducaoController;
import br.com.projeto.data.vo.v1.ListaReproducaoVO;
import br.com.projeto.mapper.DozerMapper;
import br.com.projeto.model.ListaReproducao;
import br.com.projeto.model.Musica;
import br.com.projeto.repositories.ListaReproducaoRepository;
import br.com.projeto.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaReproducaoServices {
    @Autowired
    private ListaReproducaoRepository repository;

    @Autowired
    private MusicaRepository musicaRepository;

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

    public ResponseEntity<ListaReproducaoVO> saveListaReproducao(ListaReproducao listaReproducao) {
        try{
            if(listaReproducao == null){
                return ResponseEntity.badRequest().build();
            }
            List<Musica> musicas = listaReproducao.getMusicas();
            musicas = saveMusicas(musicas);

            listaReproducao.setMusicas(null);
            var entity = DozerMapper.parseObject(listaReproducao, ListaReproducao.class);
            entity = repository.save(entity);
            entity.setMusicas(musicas);

            var vo = DozerMapper.parseObject(repository.save(entity), ListaReproducaoVO.class);
            vo.add(linkTo(methodOn(ListaReproducaoController.class).findById(vo.getKey())).withSelfRel());
            return ResponseEntity.ok(vo);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private List<Musica> saveMusicas(List<Musica> musicas) {
        List<Musica> musicasSalvas = new ArrayList<>();
        for(Musica musica : musicas) {
            var musicaSalva = musicaRepository.save(musica);
            musicasSalvas.add(musicaSalva);
        }
        return musicasSalvas;
    }

    public ListaReproducaoVO findById(Long key) {
        var listaReproducao = repository.findById(key).get();
        if (listaReproducao != null) {
            return DozerMapper.parseObject(listaReproducao, ListaReproducaoVO.class);
        }else{
            return null;
        }
    }
}
