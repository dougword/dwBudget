package br.com.douglaswordell.dwbudget.controller.dto.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.douglaswordell.dwbudget.entity.TipoStatus;
import lombok.Data;

@Data
public class LancamentoInDTO {

	@NotNull
	private Long idCategoria;

	@NotNull
	private Long idConta;
	
	private Long idProjecao;

	@NotEmpty
	@Column(length = 100, nullable = false)
	private String item;

	@NotNull
	private LocalDate dataVencimento;

	private LocalDate dataLancado;

	@NotNull
	private BigDecimal valor;

	private BigDecimal valorLancado;

	@NotNull
	private TipoStatus status;
	
}
