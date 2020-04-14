package br.com.douglaswordell.dwbudget.entity;

public enum TipoOperacao {

	ENTRADA("E", "Entrada"), SAIDA("S", "Saída");

	private String valor;
	private String descricao;

	TipoOperacao(String valor, String descricao) {
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
