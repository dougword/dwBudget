package br.com.douglaswordell.dwbudget.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import br.com.douglaswordell.dwbudget.controller.dto.LancamentoDTO;
import br.com.douglaswordell.dwbudget.controller.dto.LancamentoInDTO;
import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.entity.Lancamento;
import br.com.douglaswordell.dwbudget.entity.Projecao;
import br.com.douglaswordell.dwbudget.service.LancamentoService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "lancamento")
public class LancamentoController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<LancamentoDTO> obterLista() {
		List<Lancamento> lancamentos = lancamentoService.obterLista();
		return toDTO(lancamentos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LancamentoDTO> obterPorId(@PathVariable Long id) {
		Optional<Lancamento> lancamento = lancamentoService.obterPorId(id);
		if (!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LancamentoDTO lancamentoDTO = toDTO(lancamento.get());
		return ResponseEntity.ok(lancamentoDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public LancamentoDTO inserir(@Valid @RequestBody LancamentoInDTO lancamentoInDTO) {
		Lancamento lancamento = fromDTO(lancamentoInDTO);
		lancamento = lancamentoService.inserir(lancamento);
		lancamento = lancamentoService.obterPorId(lancamento.getId()).get();
		LancamentoDTO lancamentoDTO = toDTO(lancamento);
		return lancamentoDTO;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public LancamentoDTO atualizar(@Valid @RequestBody LancamentoInDTO lancamentoInDTO, @PathVariable Long id) {
		Lancamento lancamento = fromDTO(lancamentoInDTO);
		lancamento.setId(id);
		lancamento = lancamentoService.atualizar(lancamento);
		LancamentoDTO lancamentoDTO = toDTO(lancamento);
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
	
	private Lancamento fromDTO(LancamentoInDTO lancamentoInDTO) {
		Lancamento lancamento = modelMapper.map(lancamentoInDTO, Lancamento.class);
		lancamento.setId(null);
		lancamento.setCategoria(new Categoria());
		lancamento.getCategoria().setId(lancamentoInDTO.getIdCategoria());
		lancamento.setConta(new Conta());
		lancamento.getConta().setId(lancamentoInDTO.getIdConta());
		if (lancamentoInDTO.getIdProjecao() != null) {
			lancamento.setProjecao(new Projecao());
			lancamento.getProjecao().setId(lancamentoInDTO.getIdProjecao()); 
		}
		return lancamento;
	}
	
	private LancamentoDTO toDTO(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = modelMapper.map(lancamento, LancamentoDTO.class);
		return lancamentoDTO;
	}
	
	private List<LancamentoDTO> toDTO(List<Lancamento> lancamentos) {
		return lancamentos
				.stream()
				.map(lancamento -> toDTO(lancamento))
				.collect(Collectors.toList());
	}

}
