package br.com.douglaswordell.dwbudget.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoOperacaoConverter implements AttributeConverter<TipoOperacao, String> {

	@Override
	public String convertToDatabaseColumn(TipoOperacao attribute) {
		return attribute.getValor();
	}

	@Override
	public TipoOperacao convertToEntityAttribute(String dbData) {
		for (TipoOperacao tipoOperacao : TipoOperacao.values()) {
			if (tipoOperacao.getValor().equals(dbData))
				return tipoOperacao;
		}
		throw new RuntimeException("Valor não permitido");
	}

}
