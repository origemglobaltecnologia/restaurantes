package br.com.administracao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Cardapio;
import br.com.administracao.util.ConectaMySQL;

public class CardapioDAOImpl implements CardapioDAO {
	private Connection conn = ConectaMySQL.getConnection();

	static CardapioDAOImpl cardapioDAOImpl = new CardapioDAOImpl();
	CardapioDAO cardapioDao = cardapioDAOImpl;
	
	@Override
	public void addCardapio(Cardapio c) {
		String sql = "INSERT INTO cardapio(titulo, descricao, setor, status, valor, data) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, c.getTitulo());
			preparador.setString(2, c.getDescricao());
			preparador.setString(3, c.getSetor());
			preparador.setString(4, c.getStatus());
			preparador.setBigDecimal(5, c.getValor());
			preparador.setString(6, c.getData());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}				
	}

	@Override
	public void updateCardapio(Cardapio c) {
		String sql ="UPDATE cardapio SET titulo=?, descricao=?, setor=?, status=?, valor=?, data=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, c.getTitulo());
			preparador.setString(2, c.getDescricao());
			preparador.setString(3, c.getSetor());
			preparador.setString(4, c.getStatus());
			preparador.setBigDecimal(5, c.getValor());
			preparador.setString(6, c.getData());
			preparador.setInt(7, c.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}				
	}

	@Override
	public List<Cardapio> listCardapios() {
		String sql = "SELECT * FROM cardapio ORDER BY id DESC";
		List<Cardapio> listCardapios = new ArrayList<Cardapio>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Cardapio cardapio = new Cardapio();
				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setData(resultado.getString("data"));
				cardapio.setSetor(resultado.getString("setor"));
				cardapio.setStatus(resultado.getString("status"));
				cardapio.setValor(resultado.getBigDecimal("valor"));
				listCardapios.add(cardapio);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listCardapios;
	}

	@Override
	public Cardapio getCardapioById(int id) {
		String sql = "SELECT * FROM cardapio WHERE id = ?";
		Cardapio cardapio = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				cardapio = new Cardapio();
				cardapio.setId(resultado.getInt("id"));
				cardapio.setTitulo(resultado.getString("titulo"));
				cardapio.setDescricao(resultado.getString("descricao"));
				cardapio.setData(resultado.getString("data"));
				cardapio.setSetor(resultado.getString("setor"));
				cardapio.setStatus(resultado.getString("status"));
				cardapio.setValor(resultado.getBigDecimal("valor"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return cardapio;
	}

	@Override
	public void removeCardapio(int id) {
		String sql ="DELETE FROM cardapio WHERE id=?";
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

}
