package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.CarroDto;
import br.com.stefanini.maratonadev.model.Carro;

public class CarroParser {

    public static CarroParser get(){
        return  new CarroParser();
    }

    public CarroDto dto(Carro entidade){
        CarroDto dto = new CarroDto();

        dto.setPlaca(entidade.getPlaca());
        dto.setAno(entidade.getAno());
        dto.setModelo(entidade.getModelo());
        dto.setMarca(entidade.getMarca());
        dto.setDisponivel(entidade.getDisponivel());
        return dto;
    }
    
    public Carro entity(CarroDto dto) {
    	Carro entity = new Carro();
		
    	entity.setPlaca(dto.getPlaca());
    	entity.setAno(dto.getAno());
    	entity.setModelo(dto.getModelo());
    	entity.setMarca(dto.getMarca());
    	entity.setDisponivel(dto.getDisponivel());
		
		return entity;
	}
}
