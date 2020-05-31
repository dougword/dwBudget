package br.com.douglaswordell.dwbudget.controller.dto.projecao;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProjecaoMinDTO {
	private Long id;
	private String item;
	private BigDecimal valor;
}