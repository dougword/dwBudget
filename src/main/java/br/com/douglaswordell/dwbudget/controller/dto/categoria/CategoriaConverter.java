package br.com.douglaswordell.dwbudget.controller.dto.categoria;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.douglaswordell.dwbudget.entity.Categoria;

@Component
public class CategoriaConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public Categoria fromDTO(CategoriaInDTO categoriaInDTO) {
		return modelMapper.map(categoriaInDTO, Categoria.class);
	}
	
	public CategoriaDTO toDTO(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaDTO.class);
	}
	
	public List<CategoriaDTO> toDTO(List<Categoria> categorias) {
		return categorias
				.stream()
				.map(categoria -> toDTO(categoria))
				.collect(Collectors.toList());
	}
	
}
