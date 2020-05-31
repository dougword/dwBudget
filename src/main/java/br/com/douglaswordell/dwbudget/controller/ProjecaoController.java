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
import br.com.douglaswordell.dwbudget.controller.dto.projecao.ProjecaoConverter;
import br.com.douglaswordell.dwbudget.controller.dto.projecao.ProjecaoDTO;
import br.com.douglaswordell.dwbudget.controller.dto.projecao.ProjecaoInDTO;
import br.com.douglaswordell.dwbudget.entity.Projecao;
import br.com.douglaswordell.dwbudget.service.ProjecaoService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "projecao")
public class ProjecaoController {

	@Autowired
	private ProjecaoService projecaoService;
	
	@Autowired
	private ProjecaoConverter converter;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProjecaoDTO> obterLista() {
		List<Projecao> projecaos = projecaoService.obterLista();
		return converter.toDTO(projecaos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjecaoDTO> obterPorId(@PathVariable Long id) {
		Optional<Projecao> projecao = projecaoService.obterPorId(id);
		if (!projecao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ProjecaoDTO projecaoDTO = converter.toDTO(projecao.get());
		return ResponseEntity.ok(projecaoDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjecaoDTO inserir(@Valid @RequestBody ProjecaoInDTO projecaoInDTO) {
		Projecao projecao = converter.fromDTO(projecaoInDTO);
		projecao = projecaoService.inserir(projecao);
		projecao = projecaoService.obterPorId(projecao.getId()).get();
		ProjecaoDTO projecaoDTO = converter.toDTO(projecao);
		return projecaoDTO;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjecaoDTO atualizar(@Valid @RequestBody ProjecaoInDTO projecaoInDTO, @PathVariable Long id) {
		Projecao projecao = converter.fromDTO(projecaoInDTO);
		projecao.setId(id);
		projecao = projecaoService.atualizar(projecao);
		ProjecaoDTO projecaoDTO = converter.toDTO(projecao);
		return projecaoDTO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProjecaoDTO> excluir(@PathVariable Long id) {
		Optional<Projecao> projecao = projecaoService.obterPorId(id);
		if (!projecao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		projecaoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	


}
