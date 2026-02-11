package br.com.administracao.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.administracao.model.Caixa;

public interface CaixaSERVICE {

	public boolean addCaixa(Caixa c);
	public void updateCaixa(Caixa c);
	public void subtrairValorDoCaixa(int idCaixa, BigDecimal valorItem, BigDecimal comissaoItem);
	public List<Caixa> listCaixas();
	public Caixa getCaixaById(int id);
	public Caixa getCaixaByLastId();
	public Caixa getCaixaByLastIdAberto();
	public void removeCaixa(int id);
	
}
