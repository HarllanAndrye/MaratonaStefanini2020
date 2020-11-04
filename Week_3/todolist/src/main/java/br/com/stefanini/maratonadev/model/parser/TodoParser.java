package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.TodoDto;
import br.com.stefanini.maratonadev.model.Todo;

public class TodoParser {

	public static TodoParser get() {
		return new TodoParser();
	}
	
	public Todo entidade(TodoDto dto) {
		Todo entidade = new Todo();
		
		entidade.setId(dto.getId());
		entidade.setNome(dto.getNome());
		entidade.setDataCriacao(dto.getDataCriacao());
		
		return entidade;
	}
	
	public TodoDto dto(Todo entidade) {
		
		TodoDto dto = new TodoDto();
		
		dto.setId(entidade.getId());
		dto.setNome(entidade.getNome());
		dto.setDataCriacao(entidade.getDataCriacao());
		
		// Retorna apenas o 1º status, mesmo se a tarefa tem mais de um.
		//dto.setStatus(entidade.getStatus().get(0).toString()); // Feito até aqui na live
		// Retorna todos status atribuídos a tarefa
		//dto.setStatus(entidade.getStatus().toString());
		// Obtém o último status atribuído a tarefa
		dto.setStatus(entidade.getStatus().get(entidade.getStatus().size() - 1).toString());
		
		return dto;
	}
}
