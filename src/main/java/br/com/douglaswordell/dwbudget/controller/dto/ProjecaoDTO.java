package br.com.douglaswordell.dwbudget.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ProjecaoDTO {
	private Long id;
	private CategoriaMinDTO categoria;
	private String item;
	private Short diaVencimento;
	private BigDecimal valor;
	private LocalDate periodoInicial;
	private LocalDate periodoFinal;
}