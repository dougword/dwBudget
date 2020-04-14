package br.com.douglaswordell.dwbudget.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.exception.NaoExistenteException;
import br.com.douglaswordell.dwbudget.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	public Conta inserir(@Valid Conta conta) {
		return contaRepository.save(conta);
	}

	public Conta atualizar(@Valid Conta conta) {
		return contaRepository.save(conta);
	}

	public void excluir(Long id) {
		if (contaRepository.existsById(id)) {
			contaRepository.deleteById(id);
		} else {
			throw new NaoExistenteException("Não existe");
		}
	}

	public List<Conta> obterLista() {
		return contaRepository.findAll();
	}

	public Optional<Conta> obterPorId(Long id) {
		return contaRepository.findById(id);
	}

}
