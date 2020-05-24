package br.com.douglaswordell.dwbudget.controller.dto.categoria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.TipoOperacao;

@SpringBootTest
@ActiveProfiles("testnodatabase")
class CategoriaConverterTest {

	@Autowired
	private CategoriaConverter converter;
	
	private static Categoria modelo;
	
	@BeforeAll
	public static void setUp() {
		modelo = new Categoria("Mercado", false, TipoOperacao.SAIDA);
		modelo.setId(1L);
	}

	@Test
	@DisplayName("Deve converter CategoriaInDTO para Categoria")
	public void testCategoriaInDTOToCategoria() {
		CategoriaInDTO dtoOrigem = CategoriaInDTO.builder()
									.descricao(modelo.getDescricao())
									.tipoOperacao(modelo.getTipoOperacao())
									.transferencia(modelo.getTransferencia())
									.build();
		Categoria categoriaEsperada = modelo;
	
		Categoria categoriaObtida = converter.fromDTO(dtoOrigem);
		
		assertThat(categoriaObtida.getDescricao()).isEqualTo(categoriaEsperada.getDescricao());
		assertThat(categoriaObtida.getTipoOperacao()).isEqualTo(categoriaEsperada.getTipoOperacao());
		assertThat(categoriaObtida.getTransferencia()).isEqualTo(categoriaEsperada.getTransferencia());
	}
	
	@Test
	@DisplayName("Deve converter Categoria para CategoriaDTO")
	public void testCategoriaToCategoriaDTO() {
		Categoria categoriaOrigem = modelo;
				
		CategoriaDTO dtoEsperado = CategoriaDTO.builder()
									.id(categoriaOrigem.getId())
									.descricao(categoriaOrigem.getDescricao())
									.tipoOperacao(categoriaOrigem.getTipoOperacao())
									.transferencia(categoriaOrigem.getTransferencia())
									.build();
									
		CategoriaDTO dtoObtido = converter.toDTO(categoriaOrigem);
		
		assertThat(dtoObtido.getId()).isEqualTo(dtoEsperado.getId());
		assertThat(dtoObtido.getDescricao()).isEqualTo(dtoEsperado.getDescricao());
		assertThat(dtoObtido.getTipoOperacao()).isEqualTo(dtoEsperado.getTipoOperacao());
		assertThat(dtoObtido.getTransferencia()).isEqualTo(dtoEsperado.getTransferencia());
	}
	
	@Test
	@DisplayName("Deve converter List<Categoria> para List<CategoriaDTO>")
	public void testListCategoriaToListCategoriaDTO() {
		Categoria categoriaOrigem = modelo;
		
		CategoriaDTO dtoEsperado = CategoriaDTO.builder()
				.id(categoriaOrigem.getId())
				.descricao(categoriaOrigem.getDescricao())
				.tipoOperacao(categoriaOrigem.getTipoOperacao())
				.transferencia(categoriaOrigem.getTransferencia())
				.build();
		
		List<CategoriaDTO> dto = converter.toDTO(Arrays.asList(categoriaOrigem));
		
		assertThat(dto.size()).isEqualTo(1);
		assertThat(dto.get(0).getId()).isEqualTo(dtoEsperado.getId());
		assertThat(dto.get(0).getDescricao()).isEqualTo(dtoEsperado.getDescricao());
		assertThat(dto.get(0).getTipoOperacao()).isEqualTo(dtoEsperado.getTipoOperacao());
		assertThat(dto.get(0).getTransferencia()).isEqualTo(dtoEsperado.getTransferencia());
	}
	
}
