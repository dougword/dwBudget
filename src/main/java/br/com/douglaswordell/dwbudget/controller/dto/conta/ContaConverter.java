package br.com.douglaswordell.dwbudget.controller.dto.conta;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.douglaswordell.dwbudget.entity.Conta;

@Component
public class ContaConverter {

	@Autowired
	private ModelMapper modelMapper;

	public ContaDTO toDTO(Conta conta) {
		return modelMapper.map(conta, ContaDTO.class);
	}

	public List<ContaDTO> toDTO(List<Conta> contas) {
		return contas.stream().map(conta -> toDTO(conta)).collect(Collectors.toList());
	}

	public Conta fromDTO(ContaInDTO contaInDTO) {
		return modelMapper.map(contaInDTO, Conta.class);
	}

}
