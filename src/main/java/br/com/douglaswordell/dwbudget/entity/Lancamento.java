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
import br.com.douglaswordell.dwbudget.exception.RegraNegocioException;

@Entity
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lancamento")
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_conta", nullable = false)
	private Conta conta;

	@ManyToOne
	@JoinColumn(name = "id_projecao", nullable = true)
	private Projecao projecao;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Projecao getProjecao() {
		return projecao;
	}

	public void setProjecao(Projecao projecao) {
		this.projecao = projecao;
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

	public boolean isValido() {

		if (categoria == null || categoria.getId() == null)
			throw new RegraNegocioException("Categoria deve ser informada");

		if (conta == null || conta.getId() == null)
			throw new RegraNegocioException("Conta deve ser informada");

		if (valor.compareTo(BigDecimal.ZERO) == -1)
			throw new RegraNegocioException("Valor não pode ser negativo");

		if (dataVencimento == null)
			throw new RegraNegocioException("Data de vencimento deve ser informada");

		if (dataLancado != null && dataLancado.isBefore(dataVencimento))
			throw new RegraNegocioException("Data lançamento não pode ser inferior à data de vencimento");

		// Se NAO_REALIZADO não pode ter data lançamento nem valor lançamento
		if (status == TipoStatus.NAO_REALIZADO) {

			if (dataLancado != null) {
				throw new RegraNegocioException("Data de lançamento não pode ser informada com o status "
						+ TipoStatus.NAO_REALIZADO.getDescricao());
			}

			if (valorLancado != null) {
				throw new RegraNegocioException("Valor de lançamento não pode ser informada com o status "
						+ TipoStatus.NAO_REALIZADO.getDescricao());
			}

		}

		// Se REALIZADO não pode ter data lançamento nem valor lançamento nulos
		if (status == TipoStatus.REALIZADO) {

			if (dataLancado == null) {
				throw new RegraNegocioException(
						"Data de lançamento deve ser informada com o status " + TipoStatus.REALIZADO.getDescricao());
			}

			if (valorLancado == null) {
				throw new RegraNegocioException(
						"Valor de lançamento deve ser informado com o status " + TipoStatus.REALIZADO.getDescricao());
			}

		}

		return true;
	}

}
