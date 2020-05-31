package br.com.douglaswordell.dwbudget.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglaswordell.dwbudget.DwBudgetApplication;
import br.com.douglaswordell.dwbudget.controller.dto.conta.ContaConverter;
import br.com.douglaswordell.dwbudget.controller.dto.conta.ContaDTO;
import br.com.douglaswordell.dwbudget.controller.dto.conta.ContaInDTO;
import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.service.ContaService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaConverter converter;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ContaDTO> obterLista() {
		List<Conta> contas = contaService.obterLista();
		return converter.toDTO(contas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaDTO> obterPorId(@PathVariable Long id) {
		Optional<Conta> conta = contaService.obterPorId(id);
		if (!conta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ContaDTO contaDTO = converter.toDTO(conta.get());
		return ResponseEntity.ok(contaDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContaDTO inserir(@Valid @RequestBody ContaInDTO contaInDTO) {
		Conta conta = converter.fromDTO(contaInDTO);
		conta = contaService.inserir(conta);
		ContaDTO contaDTO = converter.toDTO(conta);
		return contaDTO;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ContaDTO atualizar(@Valid @RequestBody ContaInDTO contaInDTO, @PathVariable Long id) {
		Conta conta = converter.fromDTO(contaInDTO);
		conta.setId(id);
		conta = contaService.atualizar(conta);
		ContaDTO contaDTO = converter.toDTO(conta);
		return contaDTO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ContaDTO> excluir(@PathVariable Long id) {
		Optional<Conta> conta = contaService.obterPorId(id);
		if (!conta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		contaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
