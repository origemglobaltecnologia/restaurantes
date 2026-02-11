package br.com.administracao.dao;


import java.sql.ResultSet;
import java.util.List;

import br.com.administracao.model.Pedido;

public interface PedidoDAO {

	public void addPedido(Pedido p);
	public void updatePedido(Pedido p);
	public List<Pedido> listPedidos();
	public List<Pedido> listPedidosByLasCaixa();
	public Pedido getPedidoById(int id);
	public Pedido getPedidoByLastId();
	public void removePedido(int id);
	public List<Pedido> listPedidosByConta(int conta_id);
	public ResultSet listPedidosJOINUsuario();
	
	public Pedido getPedidoByConta(int conta);
	
}
