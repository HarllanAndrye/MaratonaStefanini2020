package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.CarroDto;
import br.com.stefanini.maratonadev.model.Carro;

/**
 * @author danilodorgam
 * @version 0.1.0
 * @created 29/10/2020 on 18:33
 */
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
        return dto;
    }
    
    public Carro entity(CarroDto dto) {
    	Carro entity = new Carro();
		
    	entity.setPlaca(dto.getPlaca());
    	entity.setAno(dto.getAno());
    	entity.setModelo(dto.getModelo());
    	entity.setMarca(dto.getMarca());
		
		return entity;
	}
}
