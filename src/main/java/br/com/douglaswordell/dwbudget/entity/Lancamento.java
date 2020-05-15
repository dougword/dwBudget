package br.com.douglaswordell.dwbudget.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.douglaswordell.dwbudget.entity.converter.TipoStatusConverter;

@Entity
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLancamento;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_conta", nullable = false)
	private Conta conta;

	@NotEmpty
	@Column(length = 100, nullable = false)
	private String item;

	@NotNull
	@Column(nullable = false)
	private LocalDate dataVencimento;

	private LocalDate dataLancado;

	@NotNull
	@Column(precision = 9, scale = 2, nullable = false)
	private BigDecimal valor;

	@Column(precision = 9, scale = 2)
	private BigDecimal valorLancado;

	@NotNull
	@Convert(converter = TipoStatusConverter.class)
	@Column(length = 1, nullable = false, columnDefinition = "CHAR(1)")
	private TipoStatus status;

	public Long getIdLancamento() {
		return idLancamento;
	}

	public void setIdLancamento(Long idLancamento) {
		this.idLancamento = idLancamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataLancado() {
		return dataLancado;
	}

	public void setDataLancado(LocalDate dataLancado) {
		this.dataLancado = dataLancado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorLancado() {
		return valorLancado;
	}

	public void setValorLancado(BigDecimal valorLancado) {
		this.valorLancado = valorLancado;
	}

	public TipoStatus getStatus() {
		return status;
	}

	public void setStatus(TipoStatus status) {
		this.status = status;
	}

}
