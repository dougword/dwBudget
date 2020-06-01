package br.com.douglaswordell.dwbudget.controller.dto.projecao;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.Projecao;

@SpringBootTest
@ActiveProfiles("testnodatabase")
class ProjecaoConverterTest {

	@Autowired
	private ProjecaoConverter converter;
	
	private Projecao modelo;
	
	@BeforeEach
	public void setUp() {
		modelo = new Projecao();
		modelo.setId(1L);
		modelo.setDiaVencimento((short) 10);
		modelo.setItem("Compras mercado");
		modelo.setPeriodoInicial(LocalDate.now().minusDays(1));
		modelo.setPeriodoFinal(LocalDate.now());
		modelo.setValor(new BigDecimal(100));
		
		Categoria categoria = new Categoria();
		categoria.setId(10L);
		categoria.setDescricao("Mercado");
		modelo.setCategoria(categoria);
	}
	
	@Test
	@DisplayName("Deve converter ProjecaoInDTO para Projecao")
	void testProjecaoInDTOToProjecao() {
		
		ProjecaoInDTO origem = ProjecaoInDTO.builder()
									.diaVencimento(modelo.getDiaVencimento())
									.idCategoria(modelo.getCategoria().getId())
									.item(modelo.getItem())
									.periodoInicial(modelo.getPeriodoInicial())
									.periodoFinal(modelo.getPeriodoFinal())
									.valor(modelo.getValor())
									.build();
		
		Projecao obtido = converter.fromDTO(origem);
		
		assertThat(obtido.getId()).isNull();
		assertThat(obtido.getDiaVencimento()).isEqualTo(origem.getDiaVencimento());
		assertThat(obtido.getCategoria().getId()).isEqualTo(origem.getIdCategoria());
		assertThat(obtido.getItem()).isEqualTo(origem.getItem());
		assertThat(obtido.getPeriodoInicial()).isEqualTo(origem.getPeriodoInicial());
		assertThat(obtido.getPeriodoFinal()).isEqualTo(origem.getPeriodoFinal());
		assertThat(obtido.getValor()).isEqualTo(origem.getValor());
	}

	@Test
	@DisplayName("Deve converter Projecao para ProjecaoDTO")
	void testProjecaoToProjecaoDTO() {

		Projecao origem = modelo;
		
		ProjecaoDTO obtido = converter.toDTO(origem);
		
		assertThat(obtido.getId()).isEqualTo(modelo.getId());
		assertThat(obtido.getCategoria().getId()).isEqualTo(modelo.getCategoria().getId());
		assertThat(obtido.getCategoria().getDescricao()).isEqualTo(modelo.getCategoria().getDescricao());
		assertThat(obtido.getItem()).isEqualTo(modelo.getItem());
		assertThat(obtido.getDiaVencimento()).isEqualTo(modelo.getDiaVencimento());
		assertThat(obtido.getValor()).isEqualTo(modelo.getValor());
		assertThat(obtido.getPeriodoInicial()).isEqualTo(modelo.getPeriodoInicial());
		assertThat(obtido.getPeriodoFinal()).isEqualTo(modelo.getPeriodoFinal());
		
	}
	@Test
	@DisplayName("Deve converter List<Projecao> para List<ProjecaoDTO>")
	void testListOfProjecaoToListOfProjecaoDTO() {
		
		List<Projecao> origem = new ArrayList<>();
		origem.add(modelo);
		
		List<ProjecaoDTO> obtido = converter.toDTO(origem);
		
		assertThat(obtido.size()).isEqualTo(1);
		assertThat(obtido.get(0).getId()).isEqualTo(modelo.getId());
		assertThat(obtido.get(0).getCategoria().getId()).isEqualTo(modelo.getCategoria().getId());
		assertThat(obtido.get(0).getCategoria().getDescricao()).isEqualTo(modelo.getCategoria().getDescricao());
		assertThat(obtido.get(0).getItem()).isEqualTo(modelo.getItem());
		assertThat(obtido.get(0).getDiaVencimento()).isEqualTo(modelo.getDiaVencimento());
		assertThat(obtido.get(0).getValor()).isEqualTo(modelo.getValor());
		assertThat(obtido.get(0).getPeriodoInicial()).isEqualTo(modelo.getPeriodoInicial());
		assertThat(obtido.get(0).getPeriodoFinal()).isEqualTo(modelo.getPeriodoFinal());
	}                     

}
