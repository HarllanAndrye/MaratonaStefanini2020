package br.com.stefanini.maratonadev.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EnderecoDto {

	@NotNull(message = "CEP é obrigatório.")
	private Integer cep;
	
	private String logradouro;
	
	private String complemento;
	
	@NotEmpty(message = "Necessário informar o bairro.")
	private String bairro;
	
	@NotEmpty(message = "Necessário informar a cidade.")
	private String cidade;
	
	@NotEmpty(message = "Necessário informar a UF.")
	@Length(min = 2, max = 2, message = "A UF deve conter 2 caracteres.")
	private String uf;


	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
