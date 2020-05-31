package br.com.douglaswordell.dwbudget.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.douglaswordell.dwbudget.controller.dto.lancamento.LancamentoConverter;
import br.com.douglaswordell.dwbudget.controller.dto.lancamento.LancamentoDTO;
import br.com.douglaswordell.dwbudget.controller.dto.lancamento.LancamentoInDTO;
import br.com.douglaswordell.dwbudget.entity.Lancamento;
import br.com.douglaswordell.dwbudget.service.LancamentoService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private LancamentoConverter converter;
//	
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public List<LancamentoDTO> obterLista() {
//		List<Lancamento> lancamentos = lancamentoService.obterLista();
//		return toDTO(lancamentos);
//	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<LancamentoDTO> obterLista(LancamentoInDTO filtro, Pageable pageable) {
		Lancamento lancamentoFiltro = converter.fromDTO(filtro); 
		Example<Lancamento> example = Example.of(lancamentoFiltro, ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues().withStringMatcher(StringMatcher.CONTAINING));
		Page<Lancamento> lancamentos = lancamentoService.obterLista(example, pageable);
		return converter.toDTO(lancamentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LancamentoDTO> obterPorId(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoService.obterPorId(id);
		if (!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LancamentoDTO lancamentoDTO = converter.toDTO(lancamento.get());
		return ResponseEntity.ok(lancamentoDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoDTO inserir(@Valid @RequestBody LancamentoInDTO lancamentoInDTO) {
		Lancamento lancamento = converter.fromDTO(lancamentoInDTO);
		lancamento = lancamentoService.inserir(lancamento);
		lancamento = lancamentoService.obterPorId(lancamento.getId()).get();
		LancamentoDTO lancamentoDTO = converter.toDTO(lancamento);
		return lancamentoDTO;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LancamentoDTO atualizar(@Valid @RequestBody LancamentoInDTO lancamentoInDTO, @PathVariable Long id) {
		Lancamento lancamento = converter.fromDTO(lancamentoInDTO);
		lancamento.setId(id);
		lancamento = lancamentoService.atualizar(lancamento);
		LancamentoDTO lancamentoDTO = converter.toDTO(lancamento);
		return lancamentoDTO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LancamentoDTO> excluir(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoService.obterPorId(id);
		if (!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		lancamentoService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
