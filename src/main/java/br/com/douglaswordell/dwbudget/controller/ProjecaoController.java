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
import br.com.douglaswordell.dwbudget.controller.dto.ProjecaoDTO;
import br.com.douglaswordell.dwbudget.controller.dto.ProjecaoInDTO;
import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.Projecao;
import br.com.douglaswordell.dwbudget.service.ProjecaoService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "projecao")
public class ProjecaoController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProjecaoService projecaoService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProjecaoDTO> obterLista() {
		List<Projecao> projecaos = projecaoService.obterLista();
		return toDTO(projecaos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProjecaoDTO> obterPorId(@PathVariable Long id) {
		Optional<Projecao> projecao = projecaoService.obterPorId(id);
		if (!projecao.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		ProjecaoDTO projecaoDTO = toDTO(projecao.get());
		return ResponseEntity.ok(projecaoDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjecaoDTO inserir(@Valid @RequestBody ProjecaoInDTO projecaoInDTO) {
		Projecao projecao = fromDTO(projecaoInDTO);
		projecao = projecaoService.inserir(projecao);
		projecao = projecaoService.obterPorId(projecao.getId()).get();
		ProjecaoDTO projecaoDTO = toDTO(projecao);
		return projecaoDTO;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjecaoDTO atualizar(@Valid @RequestBody ProjecaoInDTO projecaoInDTO, @PathVariable Long id) {
		Projecao projecao = fromDTO(projecaoInDTO);
		projecao.setId(id);
		projecao = projecaoService.atualizar(projecao);
		ProjecaoDTO projecaoDTO = toDTO(projecao);
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
	
	private Projecao fromDTO(ProjecaoInDTO projecaoInDTO) {
		Projecao projecao = modelMapper.map(projecaoInDTO, Projecao.class);
		projecao.setId(null);
		projecao.setCategoria(new Categoria());
		projecao.getCategoria().setId(projecaoInDTO.getIdCategoria());
		return projecao;
	}
	
	private ProjecaoDTO toDTO(Projecao projecao) {
		ProjecaoDTO projecaoDTO = modelMapper.map(projecao, ProjecaoDTO.class);
		projecaoDTO.setCategoria(projecao.getCategoria().getDescricao());
		return projecaoDTO;
	}
	
	private List<ProjecaoDTO> toDTO(List<Projecao> projecaos) {
		return projecaos
				.stream()
				.map(projecao -> toDTO(projecao))
				.collect(Collectors.toList());
	}

}
