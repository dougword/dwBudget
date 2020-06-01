package br.com.douglaswordell.dwbudget.controller.dto.conta;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.douglaswordell.dwbudget.entity.Conta;

@SpringBootTest
@ActiveProfiles("testnodatabase")
class ContaConverterTest {

	@Autowired
	private ContaConverter converter;
	
	private static Conta modelo;
	
	@BeforeAll
	public static void setUp() {
		modelo = new Conta("Banco", true);
		modelo.setId(1L);
	}

	@Test
	@DisplayName("Deve converter ContaInDTO para Conta")
	public void testContaInDTOToConta() {
		ContaInDTO dtoOrigem = ContaInDTO.builder()
								.descricao(modelo.getDescricao())
								.ativa(modelo.getAtiva())
								.build();
		
		Conta contaEsperada = modelo;

		Conta contaObtida = converter.fromDTO(dtoOrigem);
		
		assertThat(contaObtida.getDescricao()).isEqualTo(contaEsperada.getDescricao());
		assertThat(contaObtida.getAtiva()).isEqualTo(contaEsperada.getAtiva());
	}
	
	@Test
	@DisplayName("Deve converter Conta para ContaDTO")
	public void testContaToContaDTO() {
		Conta contaOrigem = modelo;
				
		ContaDTO dtoEsperado = ContaDTO.builder()
									.id(contaOrigem.getId())
									.descricao(contaOrigem.getDescricao())
									.ativa(modelo.getAtiva())
									.build();
									
		ContaDTO dtoObtido = converter.toDTO(contaOrigem);
		
		assertThat(dtoObtido.getId()).isEqualTo(dtoEsperado.getId());
		assertThat(dtoObtido.getDescricao()).isEqualTo(dtoEsperado.getDescricao());
		assertThat(dtoObtido.getAtiva()).isEqualTo(dtoEsperado.getAtiva());
	}
	
	@Test
	@DisplayName("Deve converter List<Conta> para List<ContaDTO>")
	public void testListContaToListContaDTO() {
		Conta contaOrigem = modelo;
		
		ContaDTO dtoEsperado = ContaDTO.builder()
				.id(contaOrigem.getId())
				.descricao(contaOrigem.getDescricao())
				.ativa(contaOrigem.getAtiva())
				.build();
		
		List<ContaDTO> dto = converter.toDTO(Arrays.asList(contaOrigem));
		
		assertThat(dto.size()).isEqualTo(1);
		assertThat(dto.get(0).getId()).isEqualTo(dtoEsperado.getId());
		assertThat(dto.get(0).getDescricao()).isEqualTo(dtoEsperado.getDescricao());
		assertThat(dto.get(0).getAtiva()).isEqualTo(dtoEsperado.getAtiva());
	}
	
}
