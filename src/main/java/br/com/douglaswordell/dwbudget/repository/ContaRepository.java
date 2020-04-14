package br.com.douglaswordell.dwbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglaswordell.dwbudget.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
