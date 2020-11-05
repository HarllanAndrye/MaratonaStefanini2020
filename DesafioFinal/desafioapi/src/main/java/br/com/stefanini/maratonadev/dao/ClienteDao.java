package br.com.stefanini.maratonadev.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.stefanini.maratonadev.model.Cliente;

@RequestScoped
public class ClienteDao {

	public List<Cliente> listarClientes() {
		return Cliente.listAll();
	}

	@Transactional
	public Long cadastrar(Cliente cliente) {
		cliente.persistAndFlush();
		return cliente.getId();
	}

	public Cliente findById(Long id) {
		return Cliente.findById(id);
	}

	public Boolean verificarSeCpfExiste(String cpf) {
		return Cliente.find("cpf", cpf).firstResult() != null;
	}

	public List<Cliente> listarClientesSemAluguel(List<Long> ids) {
		return Cliente.find("id NOT IN (?1)", ids).list();
	}

}
