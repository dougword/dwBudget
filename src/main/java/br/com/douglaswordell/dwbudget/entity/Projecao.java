package br.com.douglaswordell.dwbudget.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import br.com.douglaswordell.dwbudget.exception.RegraNegocioException;

@Entity
public class Projecao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_projecao")
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;
	
	@NotEmpty
	@Column(length = 100, nullable = false)
	private String item;
	
	@Min(1)
	@Max(31)
	private Short diaVencimento;
	
	@NotNull
	@Column(precision = 9, scale = 2, nullable = false)	
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable = false)
	private LocalDate periodoInicial;
	
	private LocalDate periodoFinal;
	
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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Short getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Short diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(LocalDate periodoInicial) { 		
		this.periodoInicial = periodoInicial.withDayOfMonth(1);
	}

	public LocalDate getPeriodoFinal() {
		return periodoFinal;
	}

	public void setPeriodoFinal(LocalDate periodoFinal) {
		this.periodoFinal = periodoFinal == null ? null : periodoFinal.withDayOfMonth(1);
	}
	
	public boolean isValida() {
		
		if (categoria == null || categoria.getId() == null) 
			throw new RegraNegocioException("Categoria deve ser informada");

		if (diaVencimento != null && (diaVencimento < 1 || diaVencimento > 31))
			throw new RegraNegocioException("Dia do vencimento deve ser nulo ou estar entre 1 e 31");
		
		if (valor.compareTo(BigDecimal.ZERO) == -1)
			throw new RegraNegocioException("Valor não pode ser negativo");
		
		if (periodoInicial == null)
			throw new RegraNegocioException("Período inicial deve ser informado");
		
		if (periodoFinal != null && periodoFinal.isBefore(periodoInicial))
			throw new RegraNegocioException("Período final não pode ser inferior ao período inicial");
		
		return true;
	}
	
}
