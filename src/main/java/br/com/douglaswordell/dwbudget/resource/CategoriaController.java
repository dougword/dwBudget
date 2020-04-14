package br.com.douglaswordell.dwbudget.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglaswordell.dwbudget.DwBudgetApplication;
import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.service.CategoriaService;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> obterLista() {
		return ResponseEntity.ok(categoriaService.obterLista());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> obterPorId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaService.obterPorId(id);
		if (!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoria.get());
	}

	@PostMapping
	public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaService.inserir(categoria));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@Valid @RequestBody Categoria categoria, @PathVariable Long id) {
		categoria.setIdCategoria(id);
		categoria = categoriaService.atualizar(categoria);
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> excluir(@PathVariable Long id) {
		categoriaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
