package br.com.administracao.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.administracao.dao.ItemDAO;
import br.com.administracao.dao.ItemDAOImpl;
import br.com.administracao.model.Item;

public class TestItemDAO {
	static ItemDAOImpl itemDAOImpl = new ItemDAOImpl();

	public static void main(String[] args) {
		//testAddItem();
		//testUpdateItem();
		//testListItems();
		//testGetItemById(1);
		//testRemoveItem(1);
		//testListItensLeftJoinCardapioLeftJoinPedido("BAR");
		//testCollectionListItensLeftJoinCardapioLeftJoinPedido("PIZZARIA");
		//testListItemsByConta(2);
		testListItemsByCaixa("2");
		//testListItemsByLastCaixa();
	    //testListItensLeftJoinCardapioByLastCaixa();
		//testListItemsLastPedido();
	}

	public static void testAddItem(){
		Item item = new Item();
		item.setConta(1);
		item.setPedido(1);
		item.setCardapio(1);
		item.setQuantidade(2);
		item.setObservacao("Teste!!!");
		item.setStatus("ABERTO");
		// DAO INSERT
		ItemDAO itemDao = itemDAOImpl;
		itemDao.addItem(item);
	}
	
	public static void testUpdateItem(){
		Item item = new Item();
		item.setId(1);
		item.setConta(1);
		item.setPedido(2);
		item.setCardapio(1);
		item.setQuantidade(2);
		item.setObservacao("Teste!!!");
		item.setStatus("ABERTO");
		// DAO INSERT
		ItemDAO itemDao = itemDAOImpl;
		itemDao.updateItem(item);
	}
	
	private static void testListItems(){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItems();
		try {
			while (resultset.next()) {
				System.out.println(
						"\nItem Id: " + resultset.getInt(1) +
						"\nItem Conta: " + resultset.getInt(2) +
						"\nItem Pedido: " + resultset.getInt(3) +
						"\nItem Sequencia: " + resultset.getInt(4) +
						"\nItem Mesa: " + resultset.getInt(5) +
						"\nItem Titulo: " + resultset.getString(6) +
						"\nItem Quantidade: " + resultset.getInt(7) +
						"\nItem Observação: " + resultset.getString(8) +
						"\nItem Valor: " + resultset.getBigDecimal(9)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testListItemsByLastCaixa(){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItems();
		try {
			while (resultset.next()) {
				System.out.println(
						"\nItem Id: " + resultset.getInt(1) +
						"\nItem Conta: " + resultset.getInt(2) +
						"\nItem Pedido: " + resultset.getInt(3) +
						"\nItem Sequencia: " + resultset.getInt(4) +
						"\nItem Mesa: " + resultset.getInt(5) +
						"\nItem Titulo: " + resultset.getString(6) +
						"\nItem Quantidade: " + resultset.getInt(7) +
						"\nItem Observação: " + resultset.getString(8) +
						"\nItem Valor: " + resultset.getBigDecimal(9)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testListItemsByCaixa(String idCaixa){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItemsByCaixa(idCaixa);
		Map<String, List<String>> listItens = new HashMap<String, List<String>>();
		try {
			while (resultset.next()) {
				List<String> itemView = new ArrayList<String>();
				itemView.add(Integer.toString(resultset.getInt(1)));
				itemView.add(Integer.toString(resultset.getInt(2)));
				itemView.add(Integer.toString(resultset.getInt(3)));
				itemView.add(resultset.getString(4).toString());
				itemView.add(resultset.getBigDecimal(5).toString());
				itemView.add(resultset.getString(7));
				itemView.add(resultset.getString(9));
				if(listItens.get(resultset.getString(4).toString()) != null){
					List<String> itemUpdate = listItens.get(resultset.getString(4).toString());
					itemView.add(Integer.toString(resultset.getInt(6)+Integer.parseInt(itemUpdate.get(7))));
					BigDecimal existente = new BigDecimal(itemUpdate.get(8));
					BigDecimal entrada = resultset.getBigDecimal(8);
					BigDecimal saida = new BigDecimal("0.00");
					saida = saida.add(existente);
					saida = saida.add(entrada);
					itemView.add(saida.toString());
				} else {
					itemView.add(Integer.toString(resultset.getInt(6)));
					itemView.add(resultset.getBigDecimal(8).toString());
				}
				listItens.put(resultset.getString(4), itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}// SELECT i.id, i.conta, i.pedido, c.titulo, c.valor, i.quantidade, i.observacao, i.valor, i.tipo
		for(Map.Entry<String, List<String>> entry : listItens.entrySet()) {
			//System.out.println(entry.getKey());
			System.out.println("\n\n\nId:" + entry.getValue().get(0));
			System.out.println("Caixa:" + entry.getValue().get(1));
			System.out.println("Conta:" + entry.getValue().get(2));
			System.out.println("Titulo:" + entry.getValue().get(3));
			System.out.println("Valor Cardapio:" + entry.getValue().get(4));
			System.out.println("Observacao:" + entry.getValue().get(5));
			System.out.println("Tipo:" + entry.getValue().get(6));
			System.out.println("Quantidade:" + entry.getValue().get(7));
			System.out.println("Valor Item:" + entry.getValue().get(8));
			  /*for(String val : entry.getValue()) {
				  System.out.println(val);
			  }*/
			
		}
	}
	
	private static void testListItensLeftJoinCardapioLeftJoinPedido(String acao){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItensLeftJoinCardapioLeftJoinPedido(acao);
		try {
			while (resultset.next()) {
				System.out.println(
						"\nItem Id: " + resultset.getInt(1) +
						"\nItem Conta: " + resultset.getInt(2) +
						"\nItem Pedido: " + resultset.getInt(3) +
						"\nItem Sequencia: " + resultset.getInt(4) +
						"\nItem Mesa: " + resultset.getInt(5) +
						"\nItem Titulo: " + resultset.getString(6) +
						"\nItem Quantidade: " + resultset.getInt(7) +
						"\nItem Observação: " + resultset.getString(8) +
						"\nItem Valor: " + resultset.getBigDecimal(9)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testCollectionListItensLeftJoinCardapioLeftJoinPedido(String setor){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItensLeftJoinCardapioLeftJoinPedido(setor);
		Map<Integer, List<String>> listItens = new HashMap<Integer, List<String>>();
		try {
			while (resultset.next()) {
				List<String> itemView = new ArrayList<String>();
				String id = Integer.toString(resultset.getInt(1));
				itemView.add(id);
				String conta = Integer.toString(resultset.getInt(2));
				itemView.add(conta);
				String pedido = Integer.toString(resultset.getInt(3));
				itemView.add(pedido);
				String sequencia = Integer.toString(resultset.getInt(4));
				itemView.add(sequencia);
				String mesa = Integer.toString(resultset.getInt(5));
				itemView.add(mesa);
				itemView.add(resultset.getString(6));
				String quantidade = Integer.toString(resultset.getInt(7));
				itemView.add(quantidade);
				itemView.add(resultset.getString(8));
				itemView.add(resultset.getBigDecimal(9).toString());
				
				listItens.put(resultset.getInt(1), itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Sequência: " + listItens.get(4).get(3));
		System.out.println("Mesa: " + listItens.get(4).get(4));
		System.out.println("Título: " + listItens.get(4).get(5));
		System.out.println("Quantidade: " + listItens.get(4).get(6));
		System.out.println("Observação: " + listItens.get(4).get(7));
		System.out.println("Valor: " + listItens.get(4).get(8));
	}
	
	private static void testGetItemById(int id){
		ItemDAO itemDao = itemDAOImpl;
		Item item = itemDao.getItemById(id);
		System.out.println(
				"\nId Item: " + item.getId() +
				"\nConta Item: " + item.getConta() +
				"\nPedido Item: " + item.getPedido() +
				"\nCardapio Item: " + item.getCardapio() +
				"\nQuantidade Item: " + item.getQuantidade() +
				"\nStatus Item: " + item.getStatus() +
				"\nObservação Item: " + item.getObservacao()
				
		);		
	}
	
	public static void testRemoveItem(int id){
		ItemDAO itemDao = itemDAOImpl;
		itemDao.removeItem(id);
	}

	private static void testListItemsByConta(int conta){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItemsByConta(conta);
		try {
			while (resultset.next()) {
				System.out.println(
						"\nItem Observação: " + resultset.getString(1) +
						"\nItem Quantidade: " + resultset.getInt(2) +
						"\nValor Cardapio: " + resultset.getBigDecimal(5).toString() +
						"\nTitulo Cardápio: " + resultset.getString(4) + 
						"\nSetor Cardápio: " + resultset.getString(6) + 
						"\nValor Item: " + resultset.getBigDecimal(3).toString()
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testListItensLeftJoinCardapioByLastCaixa(){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItensLeftJoinCardapioByLastCaixa();
		try {
			while (resultset.next()) {
				System.out.println(
						"\nItem Id: " + resultset.getInt(1) +
						"\nItem Conta: " + resultset.getInt(2) +
						"\nItem Pedido: " + resultset.getInt(3) +
						"\nCardápio Titulo: " + resultset.getString(4) +
						"\nCardápio Valor: " + resultset.getBigDecimal(5) +
						"\nItem Quantidade: " + resultset.getInt(6) +
						"\nItem Observação: " + resultset.getString(7) +
						"\nItem Valor: " + resultset.getBigDecimal(8)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testListItemsLastPedido(){
		ItemDAO itemDao = itemDAOImpl;
		ResultSet resultset = itemDao.listItemsLastPedido();
		try {
			while (resultset.next()) {
				System.out.println(
						"\nItem Id: " + resultset.getInt(1) +
						"\nItem Conta: " + resultset.getInt(2) +
						"\nItem Pedido: " + resultset.getInt(3) +
						"\nItem Sequencia: " + resultset.getInt(4) +
						"\nItem Mesa: " + resultset.getInt(5) +
						"\nItem Titulo: " + resultset.getString(6) +
						"\nItem Quantidade: " + resultset.getInt(7) +
						"\nItem Observação: " + resultset.getString(8) +
						"\nItem Valor: " + resultset.getBigDecimal(9) +
						"\nItem Tipo: " + resultset.getString(10) +
						"\nItem Status: " + resultset.getString(11) + 
						"\nItem Setor: " + resultset.getString(12)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
