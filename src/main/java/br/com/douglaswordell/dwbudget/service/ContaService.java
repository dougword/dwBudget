package br.com.douglaswordell.dwbudget.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.exception.NaoExistenteException;
import br.com.douglaswordell.dwbudget.exception.RegraNegocioException;
import br.com.douglaswordell.dwbudget.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Transactional
	public Conta inserir(@Valid Conta conta) {
		if (conta.getId() != null)
			throw new RegraNegocioException("Id deve ser nulo para inserir");
		
		return contaRepository.save(conta);
	}

	@Transactional
	public Conta atualizar(@Valid Conta conta) {
		if (conta.getId() == null)
			throw new RegraNegocioException("Id é obrigatório para atualizar");
		
		return contaRepository.save(conta);
	}

	public void excluir(Long id) {
		if (!contaRepository.existsById(id)) 
			throw new NaoExistenteException("Não existe");
		
		try {
			contaRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new RegraNegocioException("Registro não pode ser excluído pois está em uso.");
		}
	}

	@Transactional(readOnly = true)
	public List<Conta> obterLista() {
		return contaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Conta> obterPorId(Long id) {
		return contaRepository.findById(id);
	}

}
