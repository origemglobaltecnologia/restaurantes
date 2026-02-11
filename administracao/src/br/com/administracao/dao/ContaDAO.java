package br.com.administracao.dao;


import java.sql.ResultSet;
import java.util.List;

import br.com.administracao.model.Conta;

public interface ContaDAO {
	
	public void addConta(Conta c);
	public void updateConta(Conta c);
	public List<Conta> listContas();
	public List<Conta> listContasByLastCaixa();
	public List<Conta> listContasByCaixa(int caixa);
	public List<Conta> listContasAbertas();
	public List<Conta> listContasFechadas();
	public Conta getContaById(int id);
	public Conta getContaByLastId();
	public Conta getContaByMesa(int mesa);
	public Conta getContaByMesaAberta(int mesa);
	public Conta getContaBySequencia(int sequencia);
	public Conta getContaBySequenciaCaixaVigente();
	public void removeConta(int id);
	
	public ResultSet getContaByIdJoinUsuarioClientePedidosItens(int id);

}
