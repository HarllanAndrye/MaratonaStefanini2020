package br.com.stefanini.maratonadev.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.stefanini.maratonadev.model.Aluguel;
import br.com.stefanini.maratonadev.model.HistoricoAluguel;

@RequestScoped
public class AluguelDao {

	@Transactional
	public void cadastrarAluguelEHistorico(Aluguel al, HistoricoAluguel historico) {
		al.persistAndFlush();
		historico.persistAndFlush();
	}

	public List<Aluguel> listarAlugueis() {
		return Aluguel.listAll();
	}

	public Aluguel findByClienteId(Long clienteId) {
		return Aluguel.find("clienteId", clienteId).firstResult();
	}

	public Aluguel findByPlacaCarro(String placaCarro) {
		return Aluguel.find("placaCarro", placaCarro).firstResult();
	}

	public List<HistoricoAluguel> listarAlugueisCliente(Long id) {
		return HistoricoAluguel.list("clienteId", id);
	}

	public HistoricoAluguel findUltimoHistorico(Long clienteId) {
		return HistoricoAluguel.find("clienteId = ?1 AND dataDevolucao IS NULL", clienteId).firstResult();
	}

	@Transactional
	public Integer atualizarHistorico(HistoricoAluguel historico) {
		return HistoricoAluguel.update("dataDevolucao = ?1 WHERE id = ?2", historico.getDataDevolucao(), historico.getId());
	}

	@Transactional
	public Boolean devolverVeiculo(Long id) {
		return Aluguel.deleteById(id);
	}

}
