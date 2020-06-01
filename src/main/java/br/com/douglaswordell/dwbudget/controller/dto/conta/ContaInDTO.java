package br.com.douglaswordell.dwbudget.controller.dto.conta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContaInDTO {

	@NotBlank
	private String descricao;

	@NotNull
	private Boolean ativa;
}
