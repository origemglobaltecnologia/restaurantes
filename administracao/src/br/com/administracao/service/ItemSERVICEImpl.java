package br.com.administracao.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.administracao.dao.CaixaDAO;
import br.com.administracao.dao.CaixaDAOImpl;
import br.com.administracao.dao.ItemDAO;
import br.com.administracao.dao.ItemDAOImpl;
import br.com.administracao.model.Item;
import br.com.administracao.model.Pedido;

public class ItemSERVICEImpl implements ItemSERVICE {

	static ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
	ItemDAO itemDao = itemDAOImpl;
	static CaixaDAOImpl caixaDAOImpl = new CaixaDAOImpl();
	CaixaDAO caixaDao = caixaDAOImpl;
	Calendar c = Calendar.getInstance();

	/*
	 * COMMONS METHODS
	 * 		-> CRUD
	 * 
	 */
	@Override
	public void addItem(Item i) {
		int caixa = caixaDao.getCaixaByLastId().getId();
		i.setCaixa(caixa);
		i.setHora(c.get(Calendar.HOUR_OF_DAY));
		i.setMinuto(c.get(Calendar.MINUTE));
		i.setSegundo(c.get(Calendar.SECOND));
		this.itemDao.addItem(i);
	}
	
	@Override
	public Item getItemById(int id) {
		return this.itemDao.getItemById(id);
	}
	
	@Override
	public void updateItem(Item i) {
		this.itemDao.updateItem(i);
	}

	@Override
	public HashMap<String, List<String>> listItems() {
		ResultSet resultset = itemDao.listItems();
		Map<String, List<String>> listItens = new HashMap<String, List<String>>();
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
				
				String chave = Integer.toString(resultset.getInt(1));
				listItens.put(chave, itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap<String, List<String>>) listItens;
	}
	
	@Override
	public void removeItem(int id) {
		this.itemDao.removeItem(id);
	}
	// END - COMMONS METHODS

	/*
	 * BUSINNESS LAW
	 * 		-> OTHERS METHODS
	 */
	@Override
	public void addItens(String [][] itensparacadastro, Pedido pedido) {
		for(int t = 0; t < itensparacadastro.length; t++){
			// Inicia o item
			Item item = new Item();
			// Setando e inserindo os 
			item.setCardapio(Integer.parseInt(itensparacadastro[t][0]));
			item.setQuantidade(Integer.parseInt(itensparacadastro[t][1]));
			item.setObservacao(itensparacadastro[t][2]);
			item.setValor(new BigDecimal(itensparacadastro[t][4]));
			
			item.setConta(pedido.getConta());
			item.setPedido(pedido.getId());
			item.setStatus("ABERTO");
			item.setTipo(pedido.getTipo());
			item.setMesa(pedido.getMesa());
			item.setSequencia(pedido.getSequencia());

			if(item.getCardapio() == 1 || item.getCardapio() == 2){
				item.setValor(new BigDecimal(itensparacadastro[t][4]));
				item.setObservacao(itensparacadastro[t][3] + " OBS: " + itensparacadastro[t][2]);
			} 
			this.addItem(item);				
		}
	}
	
	@Override
	public List<Item> listItemsSetor(String setor) {
		return this.itemDao.listItemsSetor(setor);
	}
	
	@Override
	public List<Item> listItemsByLastCaixa() {
		return this.itemDao.listItemsByLastCaixa();
	}
	
	@Override
	public  HashMap<String, List<String>> listItensLeftJoinCardapioLeftJoinPedido(String setor) {
		ResultSet resultset = itemDao.listItensLeftJoinCardapioLeftJoinPedido(setor);
		Map<String, List<String>> listItens = new HashMap<String, List<String>>();
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
				
				String chave = Integer.toString(resultset.getInt(1));
				listItens.put(chave, itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap<String, List<String>>) listItens;
	}

	@Override
	public  HashMap<String, List<String>> listItensLeftJoinCardapioByPedido(int pedido) {
		ResultSet resultset = itemDao.listItensLeftJoinCardapioByPedido(pedido);
		Map<String, List<String>> listItens = new HashMap<String, List<String>>();
		try {
			while (resultset.next()) {
				List<String> itemView = new ArrayList<String>();
				String id = Integer.toString(resultset.getInt(1));
				itemView.add(id);
				String conta = Integer.toString(resultset.getInt(2));
				itemView.add(conta);
				String order = Integer.toString(resultset.getInt(3));
				itemView.add(order);
				String sequencia = Integer.toString(resultset.getInt(4));
				itemView.add(sequencia);
				String mesa = Integer.toString(resultset.getInt(5));
				itemView.add(mesa);
				itemView.add(resultset.getString(6));
				String quantidade = Integer.toString(resultset.getInt(7));
				itemView.add(quantidade);
				itemView.add(resultset.getString(8));
				itemView.add(resultset.getBigDecimal(9).toString());
				
				String chave = Integer.toString(resultset.getInt(1));
				listItens.put(chave, itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap<String, List<String>>) listItens;
	}
	
	@Override
	public HashMap<String, List<String>>  listItemsByConta(int conta) {
		ResultSet resultset = itemDao.listItemsByConta(conta);
		Map<String, List<String>> listConta = new HashMap<String, List<String>>();
		int contador = 0;
		try {
			while (resultset.next()) {
				List<String> itemView = new ArrayList<String>();
				itemView.add(resultset.getString(1));
				String item_quantidade = Integer.toString(resultset.getInt(2));
				itemView.add(item_quantidade);
				itemView.add(resultset.getBigDecimal(3).toString());
				itemView.add(resultset.getString(4));
				itemView.add(resultset.getBigDecimal(5).toString());
				itemView.add(resultset.getString(6));
				itemView.add(Integer.toString(resultset.getInt(7)));
				itemView.add(Integer.toString(resultset.getInt(8)));
				contador++;
				String chave = Integer.toString(contador);
				listConta.put(chave, itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap<String, List<String>>) listConta;
	}
	
	@Override
	public HashMap<String, List<String>>  listItemsByCaixa(String idCaixa) {
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
				itemView.add(resultset.getString(10));
				listItens.put(resultset.getString(4), itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap<String, List<String>>) listItens;
	}
	
	@Override
	public HashMap<String, List<String>>  listItemsByPedido(int idPedido) {
		ResultSet resultset = itemDao.listItemsByPedido(idPedido);
		Map<String, List<String>> listItens = new HashMap<String, List<String>>();
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
				String status = resultset.getString(11);
				itemView.add(status);
				
				String chave = Integer.toString(resultset.getInt(1));
				listItens.put(chave, itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (HashMap<String, List<String>>) listItens;
	}
	
	@Override
	public ResultSet listItensLeftJoinCardapioByLastCaixa() {
		return this.itemDao.listItensLeftJoinCardapioByLastCaixa();
	}
	
	@Override
	public  ResultSet listResultSetSetor(String setor) {
		return this.itemDao.listResultSetSetor(setor);
	}
	// END - BUSINNESS LAW
	
	/*
	 * INTERNAL METHODS
	 * 		-> KEEP...
	 */
	public void previnirDuplicacaoDeItens(String [][] itensparacadastro) {
		for(int t = 0; t < itensparacadastro.length; t++){

		}
	}
	
	@Override
	public ResultSet  listItemsLastPedido() {
		return this.itemDao.listItemsLastPedido();
	}
}
