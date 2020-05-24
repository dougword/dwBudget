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
import br.com.douglaswordell.dwbudget.controller.dto.categoria.CategoriaConverter;
import br.com.douglaswordell.dwbudget.controller.dto.categoria.CategoriaDTO;
import br.com.douglaswordell.dwbudget.controller.dto.categoria.CategoriaInDTO;
import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.service.CategoriaService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private CategoriaConverter converter;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoriaDTO> obterLista() {
		List<Categoria> categorias = categoriaService.obterLista();
		return converter.toDTO(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> obterPorId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaService.obterPorId(id);
		if (!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		CategoriaDTO categoriaDTO = converter.toDTO(categoria.get());
		return ResponseEntity.ok(categoriaDTO);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaDTO inserir(@Valid @RequestBody CategoriaInDTO categoriaInDTO) {
		Categoria categoria = converter.fromDTO(categoriaInDTO);
		categoria = categoriaService.inserir(categoria);
		CategoriaDTO categoriaDTO = converter.toDTO(categoria);
		return categoriaDTO;
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CategoriaDTO atualizar(@Valid @RequestBody CategoriaInDTO categoriaInDTO, @PathVariable Long id) {
		Categoria categoria = converter.fromDTO(categoriaInDTO);
		categoria.setId(id);
		categoria = categoriaService.atualizar(categoria);
		CategoriaDTO categoriaDTO = converter.toDTO(categoria);
		return categoriaDTO;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoriaDTO> excluir(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaService.obterPorId(id);
		if (!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		categoriaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
