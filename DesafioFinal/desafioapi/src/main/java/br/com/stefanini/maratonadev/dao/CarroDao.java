package br.com.stefanini.maratonadev.dao;

import br.com.stefanini.maratonadev.model.Carro;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import java.util.List;

@RequestScoped
public class CarroDao {

    public List<Carro> listar(){
        return Carro.listAll(Sort.by("modelo,marca").ascending());
    }
    
    /*
     * page: é a página atual;
     * size: é a quantidade de itens por página.
     * 
     * */
    public List<Carro> listarComPaginacao(Integer page, Integer size){
    	PanacheQuery<Carro> listOfCars = Carro.findAll();
    	return listOfCars.page(Page.of(page, size)).list();
    }
    
    public Long quantidadeDeItensNoBd() {
    	return Carro.count();
    }
    
    public Carro findByPlaca(String placa) {
		return Carro.find("placa", placa).firstResult();
	}
    
    public List<Carro> listarDisponiveis(List<String> placas) {
		return Carro.find("placa NOT IN (?1) ORDER BY modelo, marca ASC", placas).list();
	}

    @Transactional
	public String incluir(Carro carro) {
		carro.persistAndFlush();
		return carro.getPlaca();
	}

	@Transactional
	public void atualizar(Carro carro) {
		Carro.update("ano = ?1, modelo = ?2, marca = ?3 WHERE placa = ?4", 
				carro.getAno(), carro.getModelo(), carro.getMarca(), carro.getPlaca());
	}
	
	@Transactional
	public void atualizarStatus(String placa, Boolean disponivel) {
		Carro.update("disponivel = ?1 WHERE placa = ?2", disponivel, placa);
	}

	@Transactional
	public boolean excluir(String placa) {
		return Carro.delete("placa", placa) > 0;
	}
	
}
