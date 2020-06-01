package br.com.douglaswordell.dwbudget.controller.dto.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

	private Long id;

	private String descricao;

	private Boolean ativa;
	
}
