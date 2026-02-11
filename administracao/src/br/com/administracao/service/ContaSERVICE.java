package br.com.administracao.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import br.com.administracao.model.Conta;

public interface ContaSERVICE {

	/*
	 * COMMONS METHODS
	 * 		-> CRUD
	 * 
	 */
	public void addConta(Conta c);
	public Conta getContaById(int id);
	public void updateConta(Conta c);
	public List<Conta> listContas();
	public void removeConta(int id);

	/*
	 * BUSINNESS RULES
	 * 		-> OTHERS METHODS
	 */
	public Conta getContaByLastId();
	public void cancelConta(int id);
	public Conta getContaByMesa(int mesa);
	public Conta getContaByMesaAberta(int mesa);
	public Conta getContaBySequencia(int sequencia);
	public Conta getContaBySequenciaCaixaVigente();
	public List<Conta> listContasByLastCaixa();
	public List<Conta> listContasByCaixa(int caixa);
	public List<Conta> listContasAbertas();
	public List<Conta> listContasFechadas();
	public void subtrairValorDaConta(int conta, BigDecimal valorItem, BigDecimal comissaoItem);
	public HashMap<String, List<String>> getContaByIdJoinUsuarioClientePedidosItens(int id);
	
}
