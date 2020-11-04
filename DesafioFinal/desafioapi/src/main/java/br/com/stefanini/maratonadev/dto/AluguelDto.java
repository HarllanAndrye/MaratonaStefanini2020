package br.com.stefanini.maratonadev.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@SuppressWarnings("serial")
public class AluguelDto implements Serializable {

	@NotNull(message = "Necessário informar o cliente.")
	private Long clienteId;	
	
	@NotEmpty(message = "Necessário informar placa do veículo.")
	@Length(min = 7, max = 7, message = "Placa inválida, deve ter 7 caracteres.")
	private String placaCarro;
	
	@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime dataAluguel;
	
	private Integer devolucaoEmDias;
	
	/*@JsonbDateFormat("dd/MM/yyyy HH:mm")
	private LocalDateTime dataDevolucao;*/

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

	public Integer getDevolucaoEmDias() {
		return devolucaoEmDias;
	}

	public void setDevolucaoEmDias(Integer devolucaoEmDias) {
		this.devolucaoEmDias = devolucaoEmDias;
	}

	/*public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}*/
	
}
