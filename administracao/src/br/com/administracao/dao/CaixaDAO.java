package br.com.administracao.dao;


import java.util.List;

import br.com.administracao.model.Caixa;

public interface CaixaDAO {

	public void addCaixa(Caixa c);
	public void updateCaixa(Caixa c);
	public void updateValorCaixa(Caixa c);
	public List<Caixa> listCaixas();
	public Caixa getCaixaById(int id);
	public Caixa getCaixaByLastId();
	public void removeCaixa(int id);
		
}
