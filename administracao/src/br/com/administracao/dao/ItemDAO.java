package br.com.administracao.dao;


import java.sql.ResultSet;
import java.util.List;

import br.com.administracao.model.Item;

public interface ItemDAO {
	
	public void addItem(Item i);
	public void updateItem(Item i);
	public List<Item> listItemsSetor(String setor);
	public ResultSet listResultSetSetor(String setor);
	public Item getItemById(int id);
	public void removeItem(int id);
	public ResultSet listItems();
	public ResultSet listItemsByConta(int conta);
	public List<Item> listItemsByLastCaixa();
	public ResultSet listItensLeftJoinCardapioLeftJoinPedido(String setor);
	public ResultSet listItensLeftJoinCardapioByLastCaixa();
	public ResultSet listItensLeftJoinCardapioByPedido(int pedido);
	public ResultSet listItemsByPedido(int idPedido);
	public ResultSet listItemsLastPedido();
	public ResultSet listItemsByCaixa(String idCaixa);

}
