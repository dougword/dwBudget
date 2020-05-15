package br.com.douglaswordell.dwbudget.controller.dto;

import br.com.douglaswordell.dwbudget.entity.TipoOperacao;
import lombok.Data;

@Data
public class CategoriaDTO {
	
	private Long id;

	private String descricao;

	private Boolean transferencia;

	private TipoOperacao tipoOperacao;

}
