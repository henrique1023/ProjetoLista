package br.com.projeto.data.vo.v1;

import br.com.projeto.model.Musica;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

public class ListaReproducaoVO extends RepresentationModel<ListaReproducaoVO> implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String nome;
    private String descricao;
    private List<Musica> musicas;

    public ListaReproducaoVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}
