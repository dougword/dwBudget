package br.com.douglaswordell.dwbudget.exception;

public class NaoExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
