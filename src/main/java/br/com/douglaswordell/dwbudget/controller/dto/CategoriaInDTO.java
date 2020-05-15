package br.com.douglaswordell.dwbudget.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.douglaswordell.dwbudget.entity.TipoOperacao;
import lombok.Data;

@Data
public class CategoriaInDTO {

	@NotEmpty
	@Size(max = 100)
	private String descricao;

	@NotNull
	private Boolean transferencia;

	@NotNull
	private TipoOperacao tipoOperacao;

	
}
