package br.com.stefanini.maratonadev.service;

import br.com.stefanini.maratonadev.dao.AluguelDao;
import br.com.stefanini.maratonadev.dao.CarroDao;
import br.com.stefanini.maratonadev.dto.CarroDto;
import br.com.stefanini.maratonadev.model.Aluguel;
import br.com.stefanini.maratonadev.model.Carro;
import br.com.stefanini.maratonadev.model.parser.CarroParser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestScoped
public class CarroService {
    
	@Inject
    CarroDao dao;
	
	@Inject
	AluguelDao daoAluguel;
	
	@Inject
	Validator validator;


    public List<CarroDto> listar(){
        return dao.listar()
        		.stream()
        		.map(CarroParser.get()::dto)
        		.collect(Collectors.toList());
    }
    
    public List<CarroDto> listarDisponiveis() {
    	List<String> placas = new ArrayList<>();
		
		List<Aluguel> alugueis = daoAluguel.listarAlugueis();
		alugueis.stream()
			.forEach( al -> placas.add(al.getPlacaCarro()) );
		
		return dao
				.listarDisponiveis(placas)
				.stream()
				.map(CarroParser.get()::dto)
				.collect(Collectors.toList());
	}

    @Transactional(rollbackOn = Exception.class)
	public String incluir(CarroDto dto) {
		Carro carro = CarroParser.get().entity(dto);
		
		String retorno = "";
		
		// Não inserir carro já existente (mesma placa)
		if(dao.findByPlaca(carro.getPlaca()) != null) {
			// Formatando a placa
			String placa = carro.getPlaca();
			StringBuilder stringBuilder = new StringBuilder(placa);
	        stringBuilder.insert(placa.length() - 4, '-');
	        
			return "Carro com a placa " + stringBuilder.toString() + " já está cadastrado!";
		}
		
		if (dao.incluir(carro).isEmpty()) {
			retorno = "Erro ao tentar inserir registro no banco de dados!";
		}
		
		return retorno;
	}

    @Transactional(rollbackOn = Exception.class)
	public void atualizar(String placa, CarroDto dto) {
    	Carro carro = CarroParser.get().entity(dto);
		
    	Carro carroBD = findByPlaca(placa); // Carro encontrado no banco de dados
    	carroBD.setAno(carro.getAno());
    	carroBD.setMarca(carro.getMarca());
    	carroBD.setModelo(carro.getModelo());
		
		dao.atualizar(carroBD); // Inserindo a alteração no BD
	}
    
    private Carro findByPlaca(String placa) {
    	Carro carro = dao.findByPlaca(placa);
		
		if(carro == null) {
			throw new NotFoundException();
		}
		
		return carro;
	}

	public CarroDto listarPorPlaca(String placa) {
		return CarroParser.get().dto(findByPlaca(placa));
	}

	public boolean excluir(String placa) {
		findByPlaca(placa); // Se não existir o carro, será lançada uma exceção
		
		return dao.excluir(placa);
	}
	
	public List<String> validateDto(CarroDto dto) {
		Set<ConstraintViolation<CarroDto>> errors = validator.validate(dto);
		
		List<String> errorsList = new ArrayList<>();
		
		// Se existir algum erro, insere na lista
		if (!errors.isEmpty()) {
			errorsList = errors.stream()
					.map(ConstraintViolation::getMessage)
					.collect(Collectors.toList());
		}
		
		return errorsList;
	}

}
