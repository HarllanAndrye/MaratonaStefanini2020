package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.AluguelDto;
import br.com.stefanini.maratonadev.model.Aluguel;

public class AluguelParser {
	
	public static AluguelParser get() {
		return new AluguelParser();
	}
	
	public AluguelDto returnDto(Aluguel entity) {
		
		AluguelDto dto = new AluguelDto();
		
		dto.setClienteId(entity.getClienteId());
		dto.setPlacaCarro(entity.getPlacaCarro());
		dto.setDataAluguel(entity.getDataAluguel());
		dto.setDevolucaoEmDias(entity.getDevolucaoEmDias());
		//dto.setDataDevolucao(entity.getDataDevolucao());
		
		return dto;
	}
	
	public Aluguel returnEntity(AluguelDto dto) {
		Aluguel entity = new Aluguel();
		
		entity.setClienteId(dto.getClienteId());
		entity.setPlacaCarro(dto.getPlacaCarro());
		entity.setDataAluguel(dto.getDataAluguel());
		entity.setDevolucaoEmDias(dto.getDevolucaoEmDias());
		//entity.setDataDevolucao(dto.getDataDevolucao());
		
		return entity;
	}

}
