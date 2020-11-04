package br.com.stefanini.maratonadev.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "aluguel")
public class Aluguel extends PanacheEntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "clienteId", nullable = false)
	private Long clienteId;
	
	@Column(name = "placaCarro", nullable = false)
	private String placaCarro;
	
	@Column(name = "dataAluguel", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime dataAluguel;
	
	// Atributo para informar em quantos dias o cliente tem que devolver o veículo.
	@Column(name = "devolucaoEmDias")
	private Integer devolucaoEmDias;
	
	// Se existir data de devolução, então o carro está disponível e o cliente pode alugar outro veículo
	/*@Column(name = "dataDevolucao")
	private LocalDateTime dataDevolucao;*/
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	/*
	@Override
	public String toString() {
		return "Aluguel [id=" + id + ", clienteId=" + clienteId + ", placaCarro=" + placaCarro + ", dataAluguel="
				+ dataAluguel + ", devolucaoEmDias=" + devolucaoEmDias + ", dataDevolucao=" + dataDevolucao + "]";
	}*/
	
	
	
}
