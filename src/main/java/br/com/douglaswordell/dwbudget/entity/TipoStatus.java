package br.com.douglaswordell.dwbudget.entity;

public enum TipoStatus {

	NAO_REALIZADO("N", "Não realizado"), REALIZADO("R", "Realizado"), PARCIALMENTE_REALIZADO("P", "Parcialmente realizado");

	private String valor;
	private String descricao;

	TipoStatus(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
}