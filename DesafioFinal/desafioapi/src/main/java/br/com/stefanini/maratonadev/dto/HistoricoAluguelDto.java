package br.com.stefanini.maratonadev.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;


@SuppressWarnings("serial")
public class HistoricoAluguelDto implements Serializable {

	private Long clienteId;
	
	private String placaCarro;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime dataAluguel;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime dataDevolucao;

	
	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	public LocalDateTime getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(LocalDateTime dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
}
