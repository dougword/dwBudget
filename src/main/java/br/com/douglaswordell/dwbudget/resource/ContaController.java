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
import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.service.ContaService;
import br.com.douglaswordell.dwbudget.util.GeradorURI;

@RestController
@RequestMapping(DwBudgetApplication.API_V1 + "conta")
public class ContaController {

	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<Conta>> obterLista() {
		return ResponseEntity.ok(contaService.obterLista());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> obterPorId(@PathVariable Long id) {
		Optional<Conta> conta = contaService.obterPorId(id);
		if (!conta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(conta.get());
	}
	
	@PostMapping
	public ResponseEntity<Conta> inserir(@Valid @RequestBody Conta conta) {
		conta = contaService.inserir(conta);			
		return ResponseEntity.created(GeradorURI.getById(conta.getIdConta())).body(conta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Conta> atualizar(@Valid @RequestBody Conta conta, @PathVariable Long id) {
		conta.setIdConta(id);
		conta = contaService.atualizar(conta);
		return ResponseEntity.ok(conta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Conta> excluir(@PathVariable Long id) {
		contaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}
