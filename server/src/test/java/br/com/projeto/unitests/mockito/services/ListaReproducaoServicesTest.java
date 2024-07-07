package br.com.projeto.unitests.mockito.services;

import br.com.projeto.data.vo.v1.ListaReproducaoVO;
import br.com.projeto.model.ListaReproducao;
import br.com.projeto.repositories.ListaReproducaoRepository;
import br.com.projeto.services.ListaReproducaoServices;
import br.com.projeto.unittestes.mapper.mocks.MockListaReproducao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ListaReproducaoServicesTest {

	MockListaReproducao input;

	@InjectMocks
	private ListaReproducaoServices services;
	@Mock
    ListaReproducaoRepository repository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockListaReproducao();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws Exception {
		ListaReproducao entity = input.mockEntity(1);
		entity.setId(1l);

		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		var result = services.findById(1l);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains(""));
		assertEquals("Lista Test 1" , result.getNome());
		assertEquals("Descrição Test 1" , result.getDescricao());
	}

	@Test
	void testCreate() throws Exception {
        ListaReproducao entity = input.mockEntity(1);
        ListaReproducao persisted = entity;
		persisted.setId(5l);

        ListaReproducaoVO vo = input.mockVO(1);
		vo.setKey(5l);

		when(repository.save(entity)).thenReturn(persisted);
		var result = services.saveListaReproducao(entity);
		assertNotNull(result.getBody());
		assertNotNull(result.getBody().getKey());
		assertNotNull(result.getBody().getLinks());
		assertTrue(result.getBody().toString().contains(""));
        assertEquals("Lista Test 1" , result.getBody().getNome());
        assertEquals("Descrição Test 1" , result.getBody().getDescricao());
	}

	@Test
	void testDelete() throws Exception {
        ListaReproducao entity = input.mockEntity(1);
		entity.setId(1l);

		when(repository.findByNome("Lista Test 1")).thenReturn(entity);
		services.deleteListaReproducao("Lista Test 1");
	}
	@Test
	void testFindByAll() {
        ListaReproducao entity = input.mockEntity(1);
		entity.setId(1l);
		List<ListaReproducao> listas = new ArrayList<>();
		listas.add(entity);
		when(repository.findAll()).thenReturn(listas);
		var results = services.findAllListaReproducao();
        var result = results.getBody().get(0);
		assertNotNull(result);
		assertNotNull(result.getId());
        assertEquals("Lista Test 1" , result.getNome());
        assertEquals("Descrição Test 1" , result.getDescricao());
	}
}
