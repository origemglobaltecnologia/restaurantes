package br.com.administracao.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import br.com.administracao.model.Conta;
import br.com.administracao.model.Pedido;

public interface PedidoSERVICE {

	/*
	 * COMMONS METHODS
	 * 		-> CRUD
	 * 
	 */
	public void addPedido(Pedido p);
	public void updatePedido(Pedido p);
	public List<Pedido> listPedidos();
	public Pedido getPedidoById(int id);
	public void removePedido(int id);

	/*
	 * BUSINNESS RULES
	 * 		-> OTHERS METHODS
	 */
	public void lancarPedido(Pedido p, String [][] itens, Conta c);
	public Pedido getPedidoByLastId();
	public void cancelPedido(int id);
	public void cancelPedidosByConta(int idPedido);
	public Pedido getPedidoByConta(int conta);
	public void subtrairValorDoPedido(int idPedido, BigDecimal valorItem);
	public List<Pedido> listPedidosByLasCaixa();
	public List<Pedido> listPedidosByConta(int conta_id);	
	public ResultSet listPedidosJOINUsuario();
	
	
}
