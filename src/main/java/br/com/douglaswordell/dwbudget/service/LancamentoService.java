package br.com.douglaswordell.dwbudget.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.douglaswordell.dwbudget.entity.Lancamento;
import br.com.douglaswordell.dwbudget.exception.NaoExistenteException;
import br.com.douglaswordell.dwbudget.exception.RegraNegocioException;
import br.com.douglaswordell.dwbudget.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Transactional
	public Lancamento inserir(Lancamento lancamento) {
		if (lancamento.getId() != null)
			throw new RegraNegocioException("Id deve ser nulo para inserir");
		
		if (!lancamento.isValido())
			throw new RegraNegocioException("Lançamento inválido");
		
		return lancamentoRepository.save(lancamento);
	}
	
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		if (lancamento.getId() == null)
			throw new RegraNegocioException("Id é obrigatório para atualizar");
		
		if (!lancamento.isValido())
			throw new RegraNegocioException("Lançamento inválido");
		
		return lancamentoRepository.save(lancamento);
	}
	
	public void excluir(Long id) {
		if (!lancamentoRepository.existsById(id)) 
			throw new NaoExistenteException("Não existe");
		
		try {
			lancamentoRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new RegraNegocioException("Registro não pode ser excluído pois está em uso.");
		}
	}

	@Transactional(readOnly = true)
	public List<Lancamento> obterLista() {
		return lancamentoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Lancamento> obterLista(Example<Lancamento> example, Pageable pageable) {
		return lancamentoRepository.findAll(example, pageable);
	}

	@Transactional(readOnly = true)
	public Optional<Lancamento> obterPorId(Long id) {
		return lancamentoRepository.findById(id);
	}
	
}
