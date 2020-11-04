package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.ClienteDto;
import br.com.stefanini.maratonadev.dto.EnderecoDto;
import br.com.stefanini.maratonadev.model.Cliente;
import br.com.stefanini.maratonadev.model.Endereco;

public class ClienteParser {

	public static ClienteParser get() {
		return new ClienteParser();
	}
	
	public ClienteDto returnDto(Cliente entity) {
		ClienteDto dto = new ClienteDto();
		
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCpf(entity.getCpf());
		dto.setEmail(entity.getEmail());
		dto.setContato(entity.getContato());
		
		EnderecoDto endereco = new EnderecoDto();
		endereco.setCep(entity.getEndereco().getCep());
		endereco.setLogradouro(entity.getEndereco().getLogradouro());
		endereco.setComplemento(entity.getEndereco().getComplemento());
		endereco.setBairro(entity.getEndereco().getBairro());
		endereco.setCidade(entity.getEndereco().getCidade());
		endereco.setUf(entity.getEndereco().getUf());
		dto.setEndereco(endereco);
		
		return dto;
	}
	
	public Cliente returnEntity(ClienteDto dto) {
		Cliente entity = new Cliente();
		
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());
		entity.setEmail(dto.getEmail());
		entity.setContato(dto.getContato());
		
		Endereco endereco = new Endereco();
		endereco.setCep(dto.getEndereco().getCep());
		endereco.setLogradouro(dto.getEndereco().getLogradouro());
		endereco.setComplemento(dto.getEndereco().getComplemento());
		endereco.setBairro(dto.getEndereco().getBairro());
		endereco.setCidade(dto.getEndereco().getCidade());
		endereco.setUf(dto.getEndereco().getUf());
		entity.setEndereco(endereco);
		
		return entity;
	}
	
}
