package br.com.administracao.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import br.com.administracao.model.Item;
import br.com.administracao.model.Pedido;

public interface ItemSERVICE {

	/*
	 * COMMONS METHODS
	 * 		-> CRUD
	 * 
	 */
	public void addItem(Item i);
	public Item getItemById(int id);
	public void updateItem(Item i);
	public HashMap<String, List<String>> listItems();
	public void removeItem(int id);
	

	/*
	 * BUSINNESS LAW
	 * 		-> OTHERS METHODS
	 */
	public void addItens(String [][] itensparacadastro, Pedido pedido);
	public List<Item> listItemsByLastCaixa();
	public List<Item> listItemsSetor(String setor);
	public HashMap<String, List<String>> listItemsByConta(int conta);
	public HashMap<String, List<String>> listItensLeftJoinCardapioLeftJoinPedido(String setor);
	public HashMap<String, List<String>> listItensLeftJoinCardapioByPedido(int pedido);
	public HashMap<String, List<String>> listItemsByPedido(int idPedido);
	public ResultSet listResultSetSetor(String setor);
	public ResultSet listItensLeftJoinCardapioByLastCaixa();
	public ResultSet listItemsLastPedido();
	public HashMap<String, List<String>> listItemsByCaixa(String idCaixa);
	
}
