package br.com.douglaswordell.dwbudget.controller.dto.projecao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProjecaoInDTO {
	
	@NotNull
	private Long idCategoria;
	
	@NotEmpty
	private String item;
	
	@Min(1)
	@Max(31)
	private Short diaVencimento;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private LocalDate periodoInicial;
	
	private LocalDate periodoFinal;
}
