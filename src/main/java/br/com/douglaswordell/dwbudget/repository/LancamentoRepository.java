package br.com.douglaswordell.dwbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglaswordell.dwbudget.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
