package br.com.douglaswordell.dwbudget.controller.dto.projecao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.Projecao;

@Component
public class ProjecaoConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Projecao fromDTO(ProjecaoInDTO projecaoInDTO) {
		Projecao projecao = modelMapper.map(projecaoInDTO, Projecao.class);
		projecao.setId(null);
		projecao.setCategoria(new Categoria());
		projecao.getCategoria().setId(projecaoInDTO.getIdCategoria());
		return projecao;
	}

	public ProjecaoDTO toDTO(Projecao projecao) {
		ProjecaoDTO projecaoDTO = modelMapper.map(projecao, ProjecaoDTO.class);
		return projecaoDTO;
	}

	public List<ProjecaoDTO> toDTO(List<Projecao> projecaos) {
		return projecaos.stream().map(projecao -> toDTO(projecao)).collect(Collectors.toList());
	}

}
