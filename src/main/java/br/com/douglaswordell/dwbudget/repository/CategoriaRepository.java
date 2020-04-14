package br.com.douglaswordell.dwbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglaswordell.dwbudget.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
