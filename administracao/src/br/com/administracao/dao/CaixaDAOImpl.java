package br.com.administracao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Caixa;
import br.com.administracao.util.ConectaMySQL;

public class CaixaDAOImpl implements CaixaDAO {
	private Connection conn = ConectaMySQL.getConnection();

	static CaixaDAOImpl caixaDAOImpl = new CaixaDAOImpl();
	
	@Override
	public void addCaixa(Caixa c) {
		String sql = "INSERT INTO caixa(dia, mes, ano, hora, minuto, segundo, usuario, status, valor) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, c.getDia());
			preparador.setInt(2, c.getMes());
			preparador.setInt(3, c.getAno());
			preparador.setInt(4, c.getHora());
			preparador.setInt(5, c.getMinuto());
			preparador.setInt(6, c.getSegundo());
			preparador.setInt(7, c.getUsuario());
			preparador.setString(8, c.getStatus());
			preparador.setBigDecimal(9, c.getValor());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public void updateCaixa(Caixa c) {
		String sql ="UPDATE caixa SET dia=?, mes=?, ano=?, hora=?, minuto=?, segundo=?, usuario=?, status=?, valor=?, sequencia=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, c.getDia());
			preparador.setInt(2, c.getMes());
			preparador.setInt(3, c.getAno());
			preparador.setInt(4, c.getHora());
			preparador.setInt(5, c.getMinuto());
			preparador.setInt(6, c.getSegundo());
			preparador.setInt(7, c.getUsuario());
			preparador.setString(8, c.getStatus());
			preparador.setBigDecimal(9, c.getValor());
			preparador.setInt(10, c.getSequencia());
			preparador.setInt(11, c.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar. Mensagem: " + e.getMessage());
		}		
	}

	@Override
	public List<Caixa> listCaixas() {
		String sql = "SELECT * FROM caixa ORDER BY id DESC";
		List<Caixa> listCaixas = new ArrayList<Caixa>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Caixa caixa = new Caixa();
				caixa = new Caixa();
				caixa.setId(resultado.getInt("id"));
				caixa.setUsuario(resultado.getInt("usuario"));
				caixa.setDia(resultado.getInt("dia"));
				caixa.setMes(resultado.getInt("mes"));
				caixa.setAno(resultado.getInt("ano"));
				caixa.setHora(resultado.getInt("hora"));
				caixa.setMinuto(resultado.getInt("minuto"));
				caixa.setSegundo(resultado.getInt("segundo"));
				caixa.setValor(resultado.getBigDecimal("valor"));
				caixa.setStatus(resultado.getString("status"));
				listCaixas.add(caixa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listCaixas;
	}

	@Override
	public Caixa getCaixaById(int id) {
		String sql = "SELECT * FROM caixa WHERE id = ?";
		Caixa caixa = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				caixa = new Caixa();
				caixa.setId(resultado.getInt("id"));
				caixa.setDia(resultado.getInt("dia"));
				caixa.setMes(resultado.getInt("mes"));
				caixa.setAno(resultado.getInt("ano"));
				caixa.setHora(resultado.getInt("hora"));
				caixa.setMinuto(resultado.getInt("minuto"));
				caixa.setSegundo(resultado.getInt("segundo"));
				caixa.setValor(resultado.getBigDecimal("valor"));
				caixa.setStatus(resultado.getString("status"));
				caixa.setUsuario(resultado.getInt("usuario"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return caixa;
	}
	
	@Override
	public Caixa getCaixaByLastId() {
		String sql = "SELECT * FROM caixa WHERE id = (SELECT MAX(id) FROM caixa)";
		Caixa caixa = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				caixa = new Caixa();
				caixa.setId(resultado.getInt("id"));
				caixa.setDia(resultado.getInt("dia"));
				caixa.setMes(resultado.getInt("mes"));
				caixa.setAno(resultado.getInt("ano"));
				caixa.setHora(resultado.getInt("hora"));
				caixa.setMinuto(resultado.getInt("minuto"));
				caixa.setSegundo(resultado.getInt("segundo"));
				caixa.setValor(resultado.getBigDecimal("valor"));
				caixa.setStatus(resultado.getString("status"));
				caixa.setUsuario(resultado.getInt("usuario"));
				caixa.setSequencia(resultado.getInt("sequencia"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return caixa;
	}
	
	@Override
	public void removeCaixa(int id) {
		String sql ="DELETE FROM caixa WHERE id=?";
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
	public void updateValorCaixa(Caixa c) {
		String sql ="UPDATE caixa SET valor=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setBigDecimal(1, c.getValor());
			preparador.setInt(2, c.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar. Mensagem: " + e.getMessage());
		}		
	}
	
}