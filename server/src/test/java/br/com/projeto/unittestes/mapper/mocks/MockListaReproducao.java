package br.com.projeto.unittestes.mapper.mocks;

import br.com.projeto.data.vo.v1.ListaReproducaoVO;
import br.com.projeto.model.ListaReproducao;

import java.util.ArrayList;
import java.util.List;

public class MockListaReproducao {


    public ListaReproducao mockEntity() {
        return mockEntity(0);
    }

    public ListaReproducaoVO mockVO() {
        return mockVO(0);
    }

    public List<ListaReproducao> mockEntityList() {
        List< ListaReproducao>  ListaReproducaos = new ArrayList< ListaReproducao>();
        for (int i = 0; i < 14; i++) {
             ListaReproducaos.add(mockEntity(i));
        }
        return  ListaReproducaos;
    }

    public List<ListaReproducaoVO> mockVOList() {
        List< ListaReproducaoVO>  ListaReproducaos = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
             ListaReproducaos.add(mockVO(i));
        }
        return  ListaReproducaos;
    }

    public  ListaReproducao mockEntity(Integer number) {
         ListaReproducao  ListaReproducao = new  ListaReproducao();
         ListaReproducao.setNome("Lista Test " + number);
         ListaReproducao.setDescricao("Descrição Test " + number);
         ListaReproducao.setId(number.longValue());
        return  ListaReproducao;
    }

    public  ListaReproducaoVO mockVO(Integer number) {
        ListaReproducaoVO  ListaReproducao = new  ListaReproducaoVO();
        ListaReproducao.setNome("Lista Test " + number);
        ListaReproducao.setDescricao("Descrição Test " + number);
        ListaReproducao.setKey(number.longValue());
        return  ListaReproducao;
    }

}
