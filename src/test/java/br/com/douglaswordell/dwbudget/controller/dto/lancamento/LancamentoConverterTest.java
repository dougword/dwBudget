package br.com.douglaswordell.dwbudget.controller.dto.lancamento;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.entity.Lancamento;
import br.com.douglaswordell.dwbudget.entity.Projecao;
import br.com.douglaswordell.dwbudget.entity.TipoStatus;

@SpringBootTest
@ActiveProfiles("testnodatabase")
class LancamentoConverterTest {

	/* A classe Lancamento possui relacionamentos com Conta, Categoria e Projecao.
	 * A Conta e Categoria sempre são obrigatórias
	 * A Projecao é opcional  */
	
	@Autowired
	private LancamentoConverter converter;
	
	private Lancamento modelo;
	
	@BeforeEach
	private void setUp() {
		modelo = new Lancamento();
		modelo.setId(1L);
		modelo.setItem("Compras do mês");
		modelo.setDataVencimento(LocalDate.now());
		modelo.setDataLancado(LocalDate.now());
		modelo.setValor(new BigDecimal(100));
		modelo.setValorLancado(new BigDecimal(100));
		modelo.setStatus(TipoStatus.REALIZADO);
		
		Categoria categoria = new Categoria();
		categoria.setId(10L);
		modelo.setCategoria(categoria);
		
		Projecao projecao = new Projecao();
		projecao.setId(20L);
		modelo.setProjecao(projecao);
		
		Conta conta = new Conta();
		conta.setId(30L);
		modelo.setConta(conta);
	}
	
	@Test
	@DisplayName("Deve converter LancamentoInDTO para Lancamento contendo Projecao")
	void testLancamentoInDTOToLancamentoContendoProjecao() {
		LancamentoInDTO dtoOrigem = LancamentoInDTO
										.builder()
										.item(modelo.getItem())
										.dataVencimento(modelo.getDataVencimento())
										.dataLancado(modelo.getDataLancado())
										.valor(modelo.getValor())
										.valorLancado(modelo.getValorLancado())
										.status(modelo.getStatus())
										.idCategoria(modelo.getCategoria().getId())
										.idConta(modelo.getConta().getId())
										.idProjecao(modelo.getProjecao().getId())
										.build();
		
		Lancamento obtido = converter.fromDTO(dtoOrigem);
		
		assertThat(obtido.getId()).isNull();
		assertThat(obtido.getItem()).isEqualTo(dtoOrigem.getItem());
		assertThat(obtido.getDataVencimento()).isEqualTo(dtoOrigem.getDataVencimento());
		assertThat(obtido.getDataLancado()).isEqualTo(dtoOrigem.getDataLancado());
		assertThat(obtido.getValor()).isEqualTo(dtoOrigem.getValor());
		assertThat(obtido.getValorLancado()).isEqualTo(dtoOrigem.getValorLancado());
		assertThat(obtido.getStatus()).isEqualTo(dtoOrigem.getStatus());
		assertThat(obtido.getCategoria().getId()).isEqualTo(dtoOrigem.getIdCategoria());
		assertThat(obtido.getConta().getId()).isEqualTo(dtoOrigem.getIdConta());
		assertThat(obtido.getProjecao().getId()).isEqualTo(dtoOrigem.getIdProjecao());
	}
	
	@Test
	@DisplayName("Deve converter LancamentoInDTO para Lancamento sem Projecao")
	void testLancamentoInDTOToLancamentoSemProjecao() {
		LancamentoInDTO dtoOrigem = LancamentoInDTO
				.builder()
				.item(modelo.getItem())
				.dataVencimento(modelo.getDataVencimento())
				.dataLancado(modelo.getDataLancado())
				.valor(modelo.getValor())
				.valorLancado(modelo.getValorLancado())
				.status(modelo.getStatus())
				.idCategoria(modelo.getCategoria().getId())
				.idConta(modelo.getConta().getId())
				.build();
		
		Lancamento obtido = converter.fromDTO(dtoOrigem);
		
		assertThat(obtido.getId()).isNull();
		assertThat(obtido.getItem()).isEqualTo(dtoOrigem.getItem());
		assertThat(obtido.getDataVencimento()).isEqualTo(dtoOrigem.getDataVencimento());
		assertThat(obtido.getDataLancado()).isEqualTo(dtoOrigem.getDataLancado());
		assertThat(obtido.getValor()).isEqualTo(dtoOrigem.getValor());
		assertThat(obtido.getValorLancado()).isEqualTo(dtoOrigem.getValorLancado());
		assertThat(obtido.getStatus()).isEqualTo(dtoOrigem.getStatus());
		assertThat(obtido.getCategoria().getId()).isEqualTo(dtoOrigem.getIdCategoria());
		assertThat(obtido.getConta().getId()).isEqualTo(dtoOrigem.getIdConta());
		assertThat(obtido.getProjecao()).isNull();
	}

	@Test
	@DisplayName("Deve converter Lancamento para LancamentoDTO contendo Projecao")
	void testLancamentoToLancamentoDTOContendoProjecao() {
		Lancamento origem = modelo;
		
		LancamentoDTO obtido = converter.toDTO(origem);
		
		assertThat(obtido.getId()).isEqualTo(origem.getId());
		
		assertThat(obtido.getCategoria().getId()).isEqualTo(origem.getCategoria().getId());
		assertThat(obtido.getCategoria().getDescricao()).isEqualTo(origem.getCategoria().getDescricao());
		
		assertThat(obtido.getConta().getId()).isEqualTo(origem.getConta().getId());
		assertThat(obtido.getConta().getDescricao()).isEqualTo(origem.getConta().getDescricao());
		
		assertThat(obtido.getProjecao().getId()).isEqualTo(origem.getProjecao().getId());
		assertThat(obtido.getProjecao().getItem()).isEqualTo(origem.getProjecao().getItem());
		assertThat(obtido.getProjecao().getValor()).isEqualTo(origem.getProjecao().getValor());		
		
		assertThat(obtido.getItem()).isEqualTo(origem.getItem());
		assertThat(obtido.getDataVencimento()).isEqualTo(origem.getDataVencimento());
		assertThat(obtido.getDataLancado()).isEqualTo(origem.getDataLancado());
		assertThat(obtido.getValor()).isEqualTo(origem.getValor());
		assertThat(obtido.getValorLancado()).isEqualTo(origem.getValorLancado());
		assertThat(obtido.getStatus()).isEqualTo(origem.getStatus());
	}
	
	@Test
	@DisplayName("Deve converter Lancamento para LancamentoDTO sem Projecao")
	void testLancamentoToLancamentoDTOSemProjecao() {
		modelo.setProjecao(null);
		Lancamento origem = modelo;
		
		LancamentoDTO obtido = converter.toDTO(origem);
		
		assertThat(obtido.getId()).isEqualTo(origem.getId());
		
		assertThat(obtido.getCategoria().getId()).isEqualTo(origem.getCategoria().getId());
		assertThat(obtido.getCategoria().getDescricao()).isEqualTo(origem.getCategoria().getDescricao());
		
		assertThat(obtido.getConta().getId()).isEqualTo(origem.getConta().getId());
		assertThat(obtido.getConta().getDescricao()).isEqualTo(origem.getConta().getDescricao());
		
		assertThat(obtido.getProjecao()).isNull();
		
		assertThat(obtido.getItem()).isEqualTo(origem.getItem());
		assertThat(obtido.getDataVencimento()).isEqualTo(origem.getDataVencimento());
		assertThat(obtido.getDataLancado()).isEqualTo(origem.getDataLancado());
		assertThat(obtido.getValor()).isEqualTo(origem.getValor());
		assertThat(obtido.getValorLancado()).isEqualTo(origem.getValorLancado());
		assertThat(obtido.getStatus()).isEqualTo(origem.getStatus());
	}

	@Test
	@DisplayName("Deve converter Page<Lancamento> para Page<LancamentoDTO>")
	void testPageOfLancamentoToPageOfLancamentoDTO() {
		List<Lancamento> lista = new ArrayList<Lancamento>(); 
		lista.add(modelo);
		
		Page<Lancamento> origem = new PageImpl<>(lista, PageRequest.of(0, 10), lista.size());
		
		Page<LancamentoDTO> obtido = converter.toDTO(origem);
		
		assertThat(obtido.getPageable().getPageNumber()).isEqualTo(0);		
		assertThat(obtido.getPageable().getPageSize()).isEqualTo(10);
		assertThat(obtido.getTotalElements()).isEqualTo(lista.size());
		assertThat(obtido.getContent().size()).isEqualTo(lista.size());
	}

}
