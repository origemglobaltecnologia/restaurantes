package br.com.administracao.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Cardapio;
import br.com.administracao.model.Item;
import br.com.administracao.util.ConectaMySQL;

public class ItemDAOImpl implements ItemDAO {
	private Connection conn = ConectaMySQL.getConnection();
	
	static ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
	
	@Override
	public void addItem(Item i) {
		String sql = "INSERT INTO item(conta, pedido, cardapio, quantidade, observacao, status, mesa, sequencia, hora, minuto, segundo, valor, caixa, tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, i.getConta());
			preparador.setInt(2, i.getPedido());
			preparador.setInt(3, i.getCardapio());
			preparador.setInt(4, i.getQuantidade());
			preparador.setString(5, i.getObservacao());
			preparador.setString(6, i.getStatus());
			preparador.setInt(7, i.getMesa());
			preparador.setInt(8, i.getSequencia());
			preparador.setInt(9, i.getHora());
			preparador.setInt(10, i.getMinuto());
			preparador.setInt(11, i.getSegundo());
			preparador.setBigDecimal(12, i.getValor());
			preparador.setInt(13, i.getCaixa());
			preparador.setString(14, i.getTipo());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}				
	}

	@Override
	public void updateItem(Item i) {
		String sql ="UPDATE item SET conta=?, pedido=?, cardapio=?, quantidade=?, observacao=?, status=?, valor=?, tipo=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, i.getConta());
			preparador.setInt(2, i.getPedido());
			preparador.setInt(3, i.getCardapio());
			preparador.setInt(4, i.getQuantidade());
			preparador.setString(5, i.getObservacao());
			preparador.setString(6, i.getStatus());
			preparador.setBigDecimal(7, i.getValor());
			preparador.setString(8, i.getTipo());
			preparador.setInt(9, i.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public ResultSet listItems() {
		String sql = "SELECT i.id, i.conta, i.pedido, p.sequencia, p.mesa, c.titulo, i.quantidade, i.observacao, i.valor, i.tipo FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id LEFT JOIN pedido as p ON i.pedido = p.id WHERE i.caixa = (SELECT MAX(id) FROM caixa)";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public List<Item> listItemsSetor(String setor) {
		String sql = "SELECT * FROM item INNER JOIN cardapio ON item.cardapio = cardapio.id INNER JOIN caixa WHERE i.caixa = (SELECT MAX(id) FROM caixa) AND cardapio.setor = ?";
		List<Item> listItens = new ArrayList<Item>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, setor);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Item item = new Item();
				item.setId(resultado.getInt("id"));
				item.setConta(resultado.getInt("conta"));
				item.setPedido(resultado.getInt("pedido"));
				item.setCardapio(resultado.getInt("cardapio"));
				item.setQuantidade(resultado.getInt("quantidade"));
				item.setObservacao(resultado.getString("Observacao"));
				item.setStatus(resultado.getString("status"));
				item.setTipo(resultado.getString("tipo"));
				item.setValor(resultado.getBigDecimal("valor"));
				listItens.add(item);
				Cardapio cardapio = new Cardapio();
				cardapio.setTitulo(resultado.getString("titulo"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listItens;
	}
	
	@Override
	public ResultSet listResultSetSetor(String setor) {
		String sql = "SELECT * FROM item INNER JOIN cardapio ON item.cardapio = cardapio.id INNER JOIN caixa WHERE caixa.id = (SELECT MAX(id) FROM caixa) AND cardapio.setor = ?";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, setor);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public ResultSet listItensLeftJoinCardapioLeftJoinPedido(String setor) {
		String sql = "SELECT i.id, i.conta, i.pedido, p.sequencia, p.mesa, c.titulo, i.quantidade, i.observacao, i.valor, i.tipo FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id LEFT JOIN pedido as p ON i.pedido = p.id WHERE c.setor = ? AND i.caixa = (SELECT MAX(id) FROM caixa)";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, setor);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}

	@Override
	public Item getItemById(int id) {
		String sql = "SELECT * FROM item WHERE id = ?";
		Item item = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				item = new Item();
				item.setId(resultado.getInt("id"));
				item.setConta(resultado.getInt("conta"));
				item.setCaixa(resultado.getInt("caixa"));
				item.setPedido(resultado.getInt("pedido"));
				item.setCardapio(resultado.getInt("cardapio"));
				item.setQuantidade(resultado.getInt("quantidade"));
				item.setObservacao(resultado.getString("Observacao"));
				item.setStatus(resultado.getString("status"));
				item.setTipo(resultado.getString("tipo"));
				item.setValor(resultado.getBigDecimal("valor"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return item;
	}

	@Override
	public void removeItem(int id) {
		String sql ="DELETE FROM item WHERE id=?";
		try 
		{
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			System.out.println("Excluido com sucesso!");
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao excluir. Mensagem: " + e.getMessage());
		}				
	}
	
	@Override
	public ResultSet listItemsByConta(int conta) {
		String sql = "SELECT i.observacao, i.quantidade, i.valor, c.titulo, c.valor, c.setor, i.mesa, i.sequencia, i.tipo FROM item AS i LEFT JOIN cardapio AS c ON i.cardapio = c.id WHERE i.conta = ? AND i.status != 'CANCELADO'";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, conta);
			resultado = preparador.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public List<Item> listItemsByLastCaixa() {
		String sql = "SELECT * item WHERE item.caixa = (SELECT MAX(id) FROM caixa);";
		List<Item> listItens = new ArrayList<Item>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Item item = new Item();
				item.setId(resultado.getInt("id"));
				item.setConta(resultado.getInt("conta"));
				item.setPedido(resultado.getInt("pedido"));
				item.setCardapio(resultado.getInt("cardapio"));
				item.setQuantidade(resultado.getInt("quantidade"));
				item.setObservacao(resultado.getString("Observacao"));
				item.setStatus(resultado.getString("status"));
				item.setTipo(resultado.getString("tipo"));
				item.setValor(resultado.getBigDecimal("valor"));
				listItens.add(item);
				Cardapio cardapio = new Cardapio();
				cardapio.setTitulo(resultado.getString("titulo"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listItens;
	}
	
	@Override
	public ResultSet listItemsByCaixa(String idCaixa) {
		String sql = "SELECT i.id, i.conta, i.pedido, c.titulo, c.valor, i.quantidade, i.observacao, i.valor, i.tipo, i.status FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id WHERE i.caixa = ? ORDER BY c.titulo ASC";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, Integer.parseInt(idCaixa));
			resultado = preparador.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public ResultSet listItensLeftJoinCardapioByLastCaixa() {
		String sql = "SELECT i.id, i.conta, i.pedido, c.titulo, c.valor, i.quantidade, i.observacao, i.valor, i.tipo FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id WHERE i.caixa = (SELECT MAX(id) FROM caixa)";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
	
	@Override
	public ResultSet listItensLeftJoinCardapioByPedido(int pedido) {
		String sql = "SELECT i.id, i.conta, i.pedido, p.sequencia, p.mesa, c.titulo, i.quantidade, i.observacao, i.valor, i.tipo FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id WHERE p.id = ?";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, pedido);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}

	@Override
	public ResultSet listItemsByPedido(int idPedido) {
		String sql = "SELECT i.id, i.conta, i.pedido, p.sequencia, p.mesa, c.titulo, i.quantidade, i.observacao, i.valor, i.tipo, i.status FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id LEFT JOIN pedido as p ON i.pedido = p.id WHERE p.id = ?";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, idPedido);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public ResultSet listItemsLastPedido() {
		String sql = "SELECT i.id, i.conta, i.pedido, p.sequencia, p.mesa, c.titulo, i.quantidade, i.observacao, i.valor, i.tipo, i.status, c.setor FROM item as i INNER JOIN cardapio as c ON i.cardapio = c.id LEFT JOIN pedido as p ON i.pedido = p.id WHERE p.id = (SELECT MAX(id) FROM pedido)";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
}
