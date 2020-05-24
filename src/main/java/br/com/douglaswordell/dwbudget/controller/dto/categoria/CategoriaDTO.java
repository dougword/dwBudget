package br.com.douglaswordell.dwbudget.controller.dto.categoria;

import br.com.douglaswordell.dwbudget.entity.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
	
	private Long id;

	private String descricao;

	private Boolean transferencia;

	private TipoOperacao tipoOperacao;

}
