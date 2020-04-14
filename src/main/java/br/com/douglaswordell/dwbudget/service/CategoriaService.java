package br.com.douglaswordell.dwbudget.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.exception.NaoExistenteException;
import br.com.douglaswordell.dwbudget.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria inserir(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public List<Categoria> obterLista() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> obterPorId(Long id) {
		return categoriaRepository.findById(id);
	}

	public Categoria atualizar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void excluir(Long id) {
		if (categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
		} else {
			throw new NaoExistenteException("Não existe");
		}
	}
	
}
