package br.com.administracao.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Conta;
import br.com.administracao.util.ConectaMySQL;

public class ContaDAOImpl implements ContaDAO {
	private Connection conn = ConectaMySQL.getConnection();

	static ContaDAOImpl caixaDAOImpl = new ContaDAOImpl();
	
	@Override
	public void addConta(Conta c) {
		String sql = "INSERT INTO conta(caixa, tipo, mesa, sequencia, usuario, valor, comissao, desconto, total, pagamento, recebido, troco, hora, minuto, segundo, observacao, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, c.getCaixa());
			preparador.setString(2, c.getTipo());
			preparador.setInt(3, c.getMesa());
			preparador.setInt(4, c.getSequencia());
			preparador.setInt(5, c.getUsuario());
			preparador.setBigDecimal(6, c.getValor());
			preparador.setBigDecimal(7, c.getComissao());
			preparador.setBigDecimal(8, c.getDesconto());
			preparador.setBigDecimal(9, c.getTotal());
			preparador.setString(10, c.getPagamento());
			preparador.setBigDecimal(11, c.getRecebido());
			preparador.setBigDecimal(12, c.getTroco());
			preparador.setInt(13, c.getHora());
			preparador.setInt(14, c.getMinuto());
			preparador.setInt(15, c.getSegundo());
			preparador.setString(16, c.getObservacao());
			preparador.setString(17, c.getStatus());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public void updateConta(Conta c) {
		String sql ="UPDATE conta SET caixa=?, tipo=?, mesa=?, sequencia=?, usuario=?, valor=?, comissao=?, desconto=?, total=?, pagamento=?, recebido=?, troco=?, observacao=?, status=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, c.getCaixa());
			preparador.setString(2, c.getTipo());
			preparador.setInt(3, c.getMesa());
			preparador.setInt(4, c.getSequencia());
			preparador.setInt(5, c.getUsuario());
			preparador.setBigDecimal(6, c.getValor());
			preparador.setBigDecimal(7, c.getComissao());
			preparador.setBigDecimal(8, c.getDesconto());
			preparador.setBigDecimal(9, c.getTotal());
			preparador.setString(10, c.getPagamento());
			preparador.setBigDecimal(11, c.getRecebido());
			preparador.setBigDecimal(12, c.getTroco());
			preparador.setString(13, c.getObservacao());
			preparador.setString(14, c.getStatus());
			preparador.setInt(15, c.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public List<Conta> listContas() {
		String sql = "SELECT * FROM conta ORDER BY id DESC";
		List<Conta> listContas = new ArrayList<Conta>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Conta conta = new Conta();
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
				listContas.add(conta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listContas;
	}
	
	@Override
	public List<Conta> listContasByLastCaixa() {
		String sql = "SELECT * FROM conta INNER JOIN caixa ON conta.caixa = caixa.id WHERE caixa.id = (SELECT MAX(id) FROM caixa)";
		List<Conta> listContas = new ArrayList<Conta>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Conta conta = new Conta();
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
				listContas.add(conta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listContas;
	}
	
	@Override
	public List<Conta> listContasByCaixa(int caixa) {
		String sql = "SELECT * FROM conta WHERE conta.caixa = ?";
		List<Conta> listContas = new ArrayList<Conta>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, caixa);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Conta conta = new Conta();
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
				listContas.add(conta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listContas;
	}
	
	@Override
	public List<Conta> listContasAbertas() {
		String sql = "SELECT * FROM conta WHERE status = 'ABERTA' AND caixa = (SELECT MAX(caixa) FROM conta) ORDER BY id DESC";
		List<Conta> listContas = new ArrayList<Conta>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Conta conta = new Conta();
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
				listContas.add(conta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listContas;
	}
	
	@Override
	public List<Conta> listContasFechadas() {
		String sql = "SELECT * FROM conta WHERE status = 'FECHADA' AND caixa = (SELECT MAX(caixa) FROM conta) ORDER BY id DESC";
		List<Conta> listContas = new ArrayList<Conta>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Conta conta = new Conta();
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
				listContas.add(conta);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listContas;
	}

	@Override
	public Conta getContaById(int id) {
		String sql = "SELECT * FROM conta WHERE id = ?";
		Conta conta = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return conta;
	}


	@Override
	public Conta getContaByLastId() {
		String sql = "SELECT * FROM conta WHERE id = (SELECT MAX(id) FROM conta)";
		Conta conta = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return conta;
	}

	
	@Override
	public Conta getContaByMesa(int mesa) {
		String sql = "SELECT * FROM conta WHERE mesa = ?";
		Conta conta = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, mesa);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return conta;
	}

	@Override
	public Conta getContaByMesaAberta(int mesa) {
		String sql = "SELECT * FROM conta WHERE mesa = ? AND status = ?";
		Conta conta = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, mesa);
			preparador.setString(2, "ABERTA");
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return conta;
	}
	
	@Override
	public Conta getContaBySequencia(int sequencia) {
		String sql = "SELECT * FROM conta WHERE sequencia = ?";
		Conta conta = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, sequencia);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return conta;
	}
	
	@Override
	public Conta getContaBySequenciaCaixaVigente() {
		// SELECT * FROM pedido INNER JOIN(conta INNER JOIN caixa ON conta.caixa = caixa.id) ON pedido.conta = conta.id WHERE caixa.id = (SELECT MAX(id) FROM caixa)
		String sql = "SELECT * FROM conta WHERE caixa = (SELECT MAX(caixa) FROM conta) AND sequencia = (SELECT MAX(sequencia) FROM conta)";
		Conta conta = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				conta = new Conta();
				conta.setId(resultado.getInt("id"));
				conta.setCaixa(resultado.getInt("caixa"));
				conta.setTipo(resultado.getString("tipo"));
				conta.setMesa(resultado.getInt("mesa"));
				conta.setSequencia(resultado.getInt("sequencia"));
				conta.setUsuario(resultado.getInt("usuario"));
				conta.setHora(resultado.getInt("hora"));
				conta.setMinuto(resultado.getInt("minuto"));
				conta.setSegundo(resultado.getInt("segundo"));
				conta.setValor(resultado.getBigDecimal("valor"));
				conta.setComissao(resultado.getBigDecimal("comissao"));
				conta.setDesconto(resultado.getBigDecimal("desconto"));
				conta.setTotal(resultado.getBigDecimal("total"));
				conta.setPagamento(resultado.getString("pagamento"));
				conta.setRecebido(resultado.getBigDecimal("recebido"));
				conta.setTroco(resultado.getBigDecimal("troco"));
				conta.setObservacao(resultado.getString("observacao"));
				conta.setStatus(resultado.getString("status"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return conta;
	}

	@Override
	public void removeConta(int id) {
		String sql ="DELETE FROM conta WHERE id=?";
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
	public ResultSet getContaByIdJoinUsuarioClientePedidosItens(int id) {
		String sql = "SELECT c.id, c.mesa, c.sequencia, c.valor, c.comissao, c.total, c.desconto, c.recebido, c.troco, c.pagamento, c.usuario, u.nome, p.id, p.cliente, a.id, a.nome, a.telefone, a.numero, a.rua, i.cardapio, i.quantidade, i.valor, i.observacao, m.titulo FROM conta AS c LEFT JOIN usuario AS u ON c.usuario = u.id RIGHT JOIN pedido AS p ON c.id = p.conta RIGHT JOIN item AS i ON p.id = i.pedido LEFT JOIN cardapio AS m ON i.cardapio = m.id LEFT JOIN cliente AS a ON p.cliente = a.id WHERE c.id = ? ";
		ResultSet resultado = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			resultado = preparador.executeQuery();
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return resultado;
	}
	
}
