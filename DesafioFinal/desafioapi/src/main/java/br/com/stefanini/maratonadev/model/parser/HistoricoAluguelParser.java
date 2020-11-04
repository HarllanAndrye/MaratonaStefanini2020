package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.HistoricoAluguelDto;
import br.com.stefanini.maratonadev.model.HistoricoAluguel;

public class HistoricoAluguelParser {
	
	public static HistoricoAluguelParser get() {
		return new HistoricoAluguelParser();
	}
	
	public HistoricoAluguelDto returnDto(HistoricoAluguel entity) {
		
		HistoricoAluguelDto dto = new HistoricoAluguelDto();
		
		dto.setClienteId(entity.getClienteId());
		dto.setPlacaCarro(entity.getPlacaCarro());
		dto.setDataAluguel(entity.getDataAluguel());
		dto.setDataDevolucao(entity.getDataDevolucao());
		
		return dto;
	}

}
