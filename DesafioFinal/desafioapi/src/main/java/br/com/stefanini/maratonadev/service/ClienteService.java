package br.com.stefanini.maratonadev.service;

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

import br.com.stefanini.maratonadev.dao.AluguelDao;
import br.com.stefanini.maratonadev.dao.CarroDao;
import br.com.stefanini.maratonadev.dao.ClienteDao;
import br.com.stefanini.maratonadev.dto.ClienteDto;
import br.com.stefanini.maratonadev.model.Aluguel;
import br.com.stefanini.maratonadev.model.Cliente;
import br.com.stefanini.maratonadev.model.parser.ClienteParser;

@RequestScoped
public class ClienteService {
	
	@Inject
	ClienteDao dao;
	
	@Inject
	CarroDao daoCarro;
	
	@Inject
	AluguelDao daoAluguel;
	
	@Inject
	Validator validator;

	public List<ClienteDto> listarClientes() {
		return dao
				.listarClientes()
				.stream()
				.map(ClienteParser.get()::returnDto)
				.collect(Collectors.toList());
	}
	
	public List<ClienteDto> listarClientesSemAluguel() {
		List<Long> ids = new ArrayList<>();
		
		List<Aluguel> alugueis = daoAluguel.listarAlugueis();
		alugueis.stream()
			.forEach( al -> ids.add(al.getClienteId()) );
		
		return dao
				.listarClientesSemAluguel(ids)
				.stream()
				.map(ClienteParser.get()::returnDto)
				.collect(Collectors.toList());
	}

	@Transactional(rollbackOn = Exception.class)
	public Long cadastrar(ClienteDto dto) {
		Cliente cliente = ClienteParser.get().returnEntity(dto);
		
		// Retorna 0 caso o CPF já exista no BD
		if (verificarSeCpfExiste(cliente.getCpf())) {
			return 0L;
		}
		
		return dao.cadastrar(cliente);
	}

	public ClienteDto listarPorId(Long id) {
		return ClienteParser.get().returnDto(findById(id));
	}
	
	public List<String> validateDto(ClienteDto dto) {
		Set<ConstraintViolation<ClienteDto>> errors = validator.validate(dto);
		
		List<String> errorsList = new ArrayList<>();
		
		// Se existir algum erro, insere na lista
		if (!errors.isEmpty()) {
			errorsList = errors.stream()
					.map(ConstraintViolation::getMessage)
					.collect(Collectors.toList());
		}
		
		return errorsList;
	}
	
	private Cliente findById(Long id) {
		Cliente cliente = dao.findById(id);
		
		if(cliente == null) {
			throw new NotFoundException();
		}
		
		return cliente;
	}
	
	private Boolean verificarSeCpfExiste(String cpf) {
		return dao.verificarSeCpfExiste(cpf);
	}

}
