package br.com.stefanini.maratonadev.dao;

import br.com.stefanini.maratonadev.model.Carro;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import java.util.List;

@RequestScoped
public class CarroDao {

    public List<Carro> listar(){
        return Carro.listAll(Sort.by("modelo,marca").ascending());
    }

    @Transactional
	public String incluir(Carro carro) {
		carro.persistAndFlush();
		return carro.getPlaca();
	}

	public Carro findByPlaca(String placa) {
		return Carro.find("placa", placa).firstResult();
	}

	@Transactional
	public void atualizar(Carro carro) {
		Carro.update("ano = ?1, modelo = ?2, marca = ?3 WHERE placa = ?4", 
				carro.getAno(), carro.getModelo(), carro.getMarca(), carro.getPlaca());
	}

	@Transactional
	public boolean excluir(String placa) {
		return Carro.delete("placa", placa) > 0;
	}

	public List<Carro> listarDisponiveis(List<String> placas) {
		return Carro.find("placa NOT IN (?1) ORDER BY modelo, marca ASC", placas).list();
	}
}
