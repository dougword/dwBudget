package br.com.douglaswordell.dwbudget.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.douglaswordell.dwbudget.entity.Projecao;
import br.com.douglaswordell.dwbudget.exception.NaoExistenteException;
import br.com.douglaswordell.dwbudget.exception.RegraNegocioException;
import br.com.douglaswordell.dwbudget.repository.ProjecaoRepository;

@Service
public class ProjecaoService {

	@Autowired
	private ProjecaoRepository projecaoRepository;

	@Transactional
	public Projecao inserir(Projecao projecao) {
		if (projecao.getId() != null)
			throw new RegraNegocioException("Id deve ser nulo para inserir");
		
		if (!projecao.isValida())
			throw new RegraNegocioException("Projeção inválida");
		
		return projecaoRepository.save(projecao);
	}
	
	@Transactional
	public Projecao atualizar(Projecao projecao) {
		if (projecao.getId() == null)
			throw new RegraNegocioException("Id é obrigatório para atualizar");
		
		if (!projecao.isValida())
			throw new RegraNegocioException("Projeção inválida");
		
		return projecaoRepository.save(projecao);
	}
	
	public void excluir(Long id) {
		if (!projecaoRepository.existsById(id)) 
			throw new NaoExistenteException("Não existe");
		
		try {
			projecaoRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new RegraNegocioException("Registro não pode ser excluído pois está em uso.");
		}
	}

	@Transactional(readOnly = true)
	public List<Projecao> obterLista() {
		return projecaoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Projecao> obterPorId(Long id) {
		return projecaoRepository.findById(id);
	}
	
}
