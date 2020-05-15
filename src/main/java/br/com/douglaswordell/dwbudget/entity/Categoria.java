package br.com.douglaswordell.dwbudget.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.douglaswordell.dwbudget.entity.converter.TipoOperacaoConverter;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long id;

	@NotEmpty
	@Column(length = 100, nullable = false)
	private String descricao;

	@NotNull
	@Column(nullable = false)
	private Boolean transferencia;

	@NotNull
	@Convert(converter = TipoOperacaoConverter.class)
	@Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
	private TipoOperacao tipoOperacao;

	public Categoria() {
	}

	public Categoria(String descricao, Boolean transferencia, TipoOperacao tipoOperacao) {
		this.descricao = descricao;
		this.transferencia = transferencia;
		this.tipoOperacao = tipoOperacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Boolean transferencia) {
		this.transferencia = transferencia;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idCategoria) {
		this.id = idCategoria;
	}

}
