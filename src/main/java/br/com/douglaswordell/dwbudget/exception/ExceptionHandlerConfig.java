package br.com.douglaswordell.dwbudget.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> erros = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String campo = ((FieldError)error).getField();
			String msg = error.getDefaultMessage();
			erros.put(campo, msg);
		});
		return ResponseEntity.badRequest().body(erros);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> erros = new HashMap<>();
		erros.put("erro", ex.getMessage());
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({
		NaoExistenteException.class
	})
	private ResponseEntity<Object> naoExistente(NaoExistenteException ex) {
		Map<String, String> erros = new HashMap<>();
		erros.put("erro", "Objeto não existente");
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({
		RegraNegocioException.class
	})
	private ResponseEntity<Object> regraNegocio(RegraNegocioException ex) {
		Map<String, String> erros = new HashMap<>();
		erros.put("erro", ex.getMessage());
		return ResponseEntity.badRequest().body(erros);
	}
	
}
