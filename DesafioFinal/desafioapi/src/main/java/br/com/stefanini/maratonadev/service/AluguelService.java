package br.com.stefanini.maratonadev.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.stefanini.maratonadev.dao.AluguelDao;
import br.com.stefanini.maratonadev.dto.AluguelDto;
import br.com.stefanini.maratonadev.dto.HistoricoAluguelDto;
import br.com.stefanini.maratonadev.model.Aluguel;
import br.com.stefanini.maratonadev.model.HistoricoAluguel;
import br.com.stefanini.maratonadev.model.parser.AluguelParser;
import br.com.stefanini.maratonadev.model.parser.HistoricoAluguelParser;

@RequestScoped
public class AluguelService {
	
	@Inject
	AluguelDao dao;
	
	@Inject
	Validator validator;
	
	
	public List<AluguelDto> listarAlugueis() {
		return dao
				.listarAlugueis()
				.stream()
				.map(AluguelParser.get()::returnDto)
				.collect(Collectors.toList());
	}

	@Transactional(rollbackOn = Exception.class)
	public String cadastrarAluguel(AluguelDto dto) {
		// Verificar se cliente ou carro já estão no BD
		if (dao.findByClienteId(dto.getClienteId()) != null) {
			return "Cliente já possui carro alugado!";
		} else if (dao.findByPlacaCarro(dto.getPlacaCarro()) != null) {
			return "Carro já está alugado!";
		}
		
		Aluguel al = AluguelParser.get().returnEntity(dto);
		
		HistoricoAluguel historico = new HistoricoAluguel();
		historico.setClienteId(al.getClienteId());
		historico.setPlacaCarro(al.getPlacaCarro());
		historico.setDataAluguel(al.getDataAluguel());
		
		dao.cadastrarAluguelEHistorico(al, historico);
		
		return "";
	}

	public List<HistoricoAluguelDto> listarAlugueisCliente(Long id) {
		return dao
				.listarAlugueisCliente(id)
				.stream()
				.map(HistoricoAluguelParser.get()::returnDto)
				.collect(Collectors.toList());
	}

	@Transactional(rollbackOn = Exception.class)
	public String devolverVeiculo(Long idCliente) {
		Aluguel al = dao.findByClienteId(idCliente);
		
		// Verifica se o cliente existe
		if (al == null) {
			return "Aluguel não encontrado!";
		}
		
		// Retorna o histórico que não tem data de devolução
		HistoricoAluguel historico = dao.findUltimoHistorico(al.getClienteId());
		LocalDateTime currentDateTime = LocalDateTime.now();		
		historico.setDataDevolucao(currentDateTime);
		
		Integer qtdRegistroAtualizado = dao.atualizarHistorico(historico); // Atualiza a data de devolução
		
		if (qtdRegistroAtualizado != 1) {
			return "Ocorreu um erro ao tentar atualizar registro! Tente novamente.";
		}
		
		// Remove do BD o registro
		return dao.devolverVeiculo(al.getId()) ? "" : "Ocorreu um erro ao tentar devolver o veículo! Tente novamente.";
	}
	
	public List<String> validateDto(AluguelDto dto) {
		Set<ConstraintViolation<AluguelDto>> errors = validator.validate(dto);
		
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
