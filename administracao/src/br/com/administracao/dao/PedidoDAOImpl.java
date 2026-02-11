package br.com.administracao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Pedido;
import br.com.administracao.util.ConectaMySQL;

public class PedidoDAOImpl implements PedidoDAO {
	private Connection conn = ConectaMySQL.getConnection();

	@Override
	public void addPedido(Pedido p) {
		String sql = "INSERT INTO pedido(tipo, mesa, sequencia, cliente, pessoas, valor, hora, minuto, segundo, conta, observacoes, usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, p.getTipo());
			preparador.setInt(2, p.getMesa());
			preparador.setInt(3, p.getSequencia());
			preparador.setInt(4, p.getCliente());
			preparador.setInt(5, p.getPessoas());
			preparador.setBigDecimal(6, p.getValor());
			preparador.setInt(7, p.getHora());
			preparador.setInt(8, p.getMinuto());
			preparador.setInt(9, p.getSegundo());
			preparador.setInt(10, p.getConta());
			preparador.setString(11, p.getObservacoes());
			preparador.setInt(12, p.getUsuario());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public void updatePedido(Pedido p) {
		String sql ="UPDATE pedido SET tipo=?, mesa=?, sequencia=?, cliente=?, pessoas=?, valor=?, hora=?, minuto=?, segundo=?, conta=?, observacoes=?, usuario=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, p.getTipo());
			preparador.setInt(2, p.getMesa());
			preparador.setInt(3, p.getSequencia());
			preparador.setInt(4, p.getCliente());
			preparador.setInt(5, p.getPessoas());
			preparador.setBigDecimal(6, p.getValor());
			preparador.setInt(7, p.getHora());
			preparador.setInt(8, p.getMinuto());
			preparador.setInt(9, p.getSegundo());
			preparador.setInt(10, p.getConta());
			preparador.setString(11, p.getObservacoes());
			preparador.setInt(12, p.getUsuario());
			preparador.setInt(13, p.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public List<Pedido> listPedidos() {
		String sql = "SELECT * FROM pedido ORDER BY id DESC";
		List<Pedido> listPedidos = new ArrayList<Pedido>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setUsuario(resultado.getInt("usuario"));
				pedido.setHora(resultado.getInt("hora"));
				pedido.setMinuto(resultado.getInt("minuto"));
				pedido.setSegundo(resultado.getInt("segundo"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTipo(resultado.getString("tipo"));
				pedido.setMesa(resultado.getInt("mesa"));
				pedido.setSequencia(resultado.getInt("sequencia"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setPessoas(resultado.getInt("pessoas"));
				pedido.setConta(resultado.getInt("conta"));
				pedido.setObservacoes(resultado.getString("observacoes"));
				listPedidos.add(pedido);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listPedidos;
	}
	
	@Override
	public List<Pedido> listPedidosByLasCaixa() {
		String sql = "SELECT * FROM pedido INNER JOIN(conta INNER JOIN caixa ON conta.caixa = caixa.id) ON pedido.conta = conta.id WHERE caixa.id = (SELECT MAX(id) FROM caixa)";
		List<Pedido> listPedidos = new ArrayList<Pedido>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setUsuario(resultado.getInt("usuario"));
				pedido.setHora(resultado.getInt("hora"));
				pedido.setMinuto(resultado.getInt("minuto"));
				pedido.setSegundo(resultado.getInt("segundo"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTipo(resultado.getString("tipo"));
				pedido.setMesa(resultado.getInt("mesa"));
				pedido.setSequencia(resultado.getInt("sequencia"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setPessoas(resultado.getInt("pessoas"));
				pedido.setConta(resultado.getInt("conta"));
				pedido.setObservacoes(resultado.getString("observacoes"));
				listPedidos.add(pedido);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listPedidos;
	}
	
	@Override
	public ResultSet listPedidosJOINUsuario() {
		
		/**
		 * 
		 * Query para ser implementada
 		SELECT 
 		p.id, p.valor, u.nome, c.nome, i.id, i.valor, m.titulo
		FROM pedido p 
		LEFT JOIN usuario u ON p.usuario = u.id 
		LEFT JOIN cliente c ON p.cliente = c.id
		LEFT JOIN item i ON i.pedido = p.id
		LEFT JOIN cardapio m ON i.cardapio = m.id;
		*/
		
		String sql = "SELECT "
						+ "p.tipo, p.mesa, p.sequencia, c.nome, "
						+ "u.nome, p.pessoas, p.valor, p.hora, "
						+ "p.minuto, p.segundo, p.conta, p.observacoes, "
						+ "ca.titulo"
						+ "FROM pedido as p "
						+ "LEFT JOIN usuario as u ON p.usuario = u.id "
						+ "LEFT JOIN cliente as c ON p.cliente = c.id "
						+ "RIGHT JOIN item as i ON p.id = i.pedido "
						+ "LEFT JOIN cardapio as ca ON i.id = ca.id "
						;
		PreparedStatement preparador;
		ResultSet resultado = null;
		try {
			preparador = (PreparedStatement) conn.prepareStatement(sql);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}


	@Override
	public Pedido getPedidoById(int id) {
		String sql = "SELECT * FROM pedido WHERE id = ?";
		Pedido pedido = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setUsuario(resultado.getInt("usuario"));
				pedido.setHora(resultado.getInt("hora"));
				pedido.setMinuto(resultado.getInt("minuto"));
				pedido.setSegundo(resultado.getInt("segundo"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTipo(resultado.getString("tipo"));
				pedido.setMesa(resultado.getInt("mesa"));
				pedido.setSequencia(resultado.getInt("sequencia"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setPessoas(resultado.getInt("pessoas"));
				pedido.setConta(resultado.getInt("conta"));
				pedido.setObservacoes(resultado.getString("observacoes"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return pedido;
	}

	@Override
	public Pedido getPedidoByLastId() {
		String sql = "SELECT * FROM pedido WHERE id =  (SELECT MAX(id) FROM pedido)";
		Pedido pedido = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setUsuario(resultado.getInt("usuario"));
				pedido.setHora(resultado.getInt("hora"));
				pedido.setMinuto(resultado.getInt("minuto"));
				pedido.setSegundo(resultado.getInt("segundo"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTipo(resultado.getString("tipo"));
				pedido.setMesa(resultado.getInt("mesa"));
				pedido.setSequencia(resultado.getInt("sequencia"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setPessoas(resultado.getInt("pessoas"));
				pedido.setConta(resultado.getInt("conta"));
				pedido.setObservacoes(resultado.getString("observacoes"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return pedido;
	}

	@Override
	public void removePedido(int id) {
		String sql ="DELETE FROM pedido WHERE id=?";
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
	public List<Pedido> listPedidosByConta(int conta_id) {
		String sql = "SELECT * FROM pedido WHERE pedido.conta = ? ORDER BY id DESC";
		List<Pedido> listPedidos = new ArrayList<Pedido>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, conta_id);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Pedido pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setUsuario(resultado.getInt("usuario"));
				pedido.setHora(resultado.getInt("hora"));
				pedido.setMinuto(resultado.getInt("minuto"));
				pedido.setSegundo(resultado.getInt("segundo"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTipo(resultado.getString("tipo"));
				pedido.setMesa(resultado.getInt("mesa"));
				pedido.setSequencia(resultado.getInt("sequencia"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setPessoas(resultado.getInt("pessoas"));
				pedido.setConta(resultado.getInt("conta"));
				pedido.setObservacoes(resultado.getString("observacoes"));
				listPedidos.add(pedido);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listPedidos;
	}
	
	@Override
	public Pedido getPedidoByConta(int conta) {
		String sql = "SELECT * FROM pedido WHERE conta = ?";
		Pedido pedido = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, conta);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				pedido = new Pedido();
				pedido.setId(resultado.getInt("id"));
				pedido.setUsuario(resultado.getInt("usuario"));
				pedido.setHora(resultado.getInt("hora"));
				pedido.setMinuto(resultado.getInt("minuto"));
				pedido.setSegundo(resultado.getInt("segundo"));
				pedido.setValor(resultado.getBigDecimal("valor"));
				pedido.setTipo(resultado.getString("tipo"));
				pedido.setMesa(resultado.getInt("mesa"));
				pedido.setSequencia(resultado.getInt("sequencia"));
				pedido.setCliente(resultado.getInt("cliente"));
				pedido.setPessoas(resultado.getInt("pessoas"));
				pedido.setConta(resultado.getInt("conta"));
				pedido.setObservacoes(resultado.getString("observacoes"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return pedido;
	}
	
}
