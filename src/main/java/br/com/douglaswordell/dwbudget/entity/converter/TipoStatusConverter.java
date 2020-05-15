package br.com.douglaswordell.dwbudget.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.douglaswordell.dwbudget.entity.TipoStatus;

@Converter
public class TipoStatusConverter implements AttributeConverter<TipoStatus, String> {

	@Override
	public String convertToDatabaseColumn(TipoStatus attribute) {
		return attribute.getValor();
	}

	@Override
	public TipoStatus convertToEntityAttribute(String dbData) {
		for (TipoStatus tipoStatus : TipoStatus.values()) {
			if (tipoStatus.getValor().equals(dbData))
				return tipoStatus;
		}
		throw new RuntimeException("Valor não permitido");
	}

}
