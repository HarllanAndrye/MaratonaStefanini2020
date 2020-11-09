package br.com.stefanini.maratonadev.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CarroDto {

	@NotEmpty(message = "A placa é obrigatória.")
	@Length(min = 7, max = 7, message = "Placa inválida, deve ter 7 caracteres.")
    private String placa;

    private int ano;

    @NotEmpty(message = "O modelo do veículo é obrigatório.")
    private String modelo;

    @NotEmpty(message = "A marca do veículo é obrigatória.")
    private String marca;
    
    private Boolean disponivel;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "\r\n{\r\n \"ano\": " + ano + 
				",\r\n \"placa\": " + placa + 
				",\r\n \"modelo\": " + modelo + 
				",\r\n \"marca\": " + marca + 
				",\r\n \"disponivel\": " + disponivel + "\r\n}";
	}
	
	
        
}
