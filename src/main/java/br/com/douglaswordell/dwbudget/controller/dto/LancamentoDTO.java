package br.com.douglaswordell.dwbudget.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.douglaswordell.dwbudget.entity.TipoStatus;
import lombok.Data;

@Data
public class LancamentoDTO {

	private Long id;
	private CategoriaMinDTO categoria;
	private ContaMinDTO conta;
	private ProjecaoMinDTO projecao;
	private String item;
	private LocalDate dataVencimento;
	private LocalDate dataLancado;
	private BigDecimal valor;
	private BigDecimal valorLancado;
	private TipoStatus status;
	
}
