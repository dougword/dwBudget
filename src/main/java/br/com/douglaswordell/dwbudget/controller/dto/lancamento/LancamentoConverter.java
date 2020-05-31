package br.com.douglaswordell.dwbudget.controller.dto.lancamento;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import br.com.douglaswordell.dwbudget.entity.Categoria;
import br.com.douglaswordell.dwbudget.entity.Conta;
import br.com.douglaswordell.dwbudget.entity.Lancamento;
import br.com.douglaswordell.dwbudget.entity.Projecao;

@Component
public class LancamentoConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public Lancamento fromDTO(LancamentoInDTO lancamentoInDTO) {
		Lancamento lancamento = modelMapper.map(lancamentoInDTO, Lancamento.class);
		lancamento.setId(null);
		lancamento.setCategoria(new Categoria());
		lancamento.getCategoria().setId(lancamentoInDTO.getIdCategoria());
		lancamento.setConta(new Conta());
		lancamento.getConta().setId(lancamentoInDTO.getIdConta());
		if (lancamentoInDTO.getIdProjecao() != null) {
			lancamento.setProjecao(new Projecao());
			lancamento.getProjecao().setId(lancamentoInDTO.getIdProjecao()); 
		}
		return lancamento;
	}
	
	public LancamentoDTO toDTO(Lancamento lancamento) {
		LancamentoDTO lancamentoDTO = modelMapper.map(lancamento, LancamentoDTO.class);
		return lancamentoDTO;
	}
	
//	private List<LancamentoDTO> toDTO(List<Lancamento> lancamentos) {
//		return lancamentos
//				.stream()
//				.map(lancamento -> toDTO(lancamento))
//				.collect(Collectors.toList());
//	}
	
	public Page<LancamentoDTO> toDTO(Page<Lancamento> lancamentos) {
		List<LancamentoDTO> listaDTO = lancamentos
			.stream()
			.map(lancamento -> toDTO(lancamento))
			.collect(Collectors.toList());
		return new PageImpl<>(listaDTO, lancamentos.getPageable(), lancamentos.getTotalElements());
	}
	
}
