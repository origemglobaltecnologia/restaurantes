package br.com.administracao.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Usuario;
import br.com.administracao.util.ConectaMySQL;

public class UsuarioDAOImpl implements UsuarioDAO {
	private Connection conn = ConectaMySQL.getConnection();

	@Override
	public void addUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuario(nome, endereco, email, senha, telefone, observacoes) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getEndereco());
			preparador.setString(3, usuario.getEmail());
			preparador.setString(4, usuario.getSenha());
			preparador.setString(5, usuario.getTelefone());
			preparador.setString(6, usuario.getObservacoes());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}				
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		String sql ="UPDATE usuario SET nome=?, endereco=?, email=?, senha=?, telefone=?, observacoes=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getEndereco());
			preparador.setString(3, usuario.getEmail());
			preparador.setString(4, usuario.getSenha());
			preparador.setString(5, usuario.getTelefone());
			preparador.setString(6, usuario.getObservacoes());
			preparador.setInt(7, usuario.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar. Mensagem: " + e.getMessage());
		}						
	}

	@Override
	public List<Usuario> listUsuarios() {
		String sql = "SELECT * FROM usuario ORDER BY id DESC";
		List<Usuario> listUsuarios = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setEndereco(resultado.getString("endereco"));
				usuario.setTelefone(resultado.getString("telefone"));
				usuario.setObservacoes(resultado.getString("observacoes"));
				listUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listUsuarios;
	}

	@Override
	public Usuario getUsuarioById(int id) {
		String sql = "SELECT * FROM usuario WHERE id = ?";
		Usuario usuario = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEndereco(resultado.getString("endereco"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setTelefone(resultado.getString("telefone"));
				usuario.setObservacoes(resultado.getString("observacoes"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return usuario;
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		String sql = "SELECT * FROM usuario WHERE email = ?";
		Usuario usuario = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, email);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEndereco(resultado.getString("endereco"));
				usuario.setEmail(resultado.getString("email"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setTelefone(resultado.getString("telefone"));
				usuario.setObservacoes(resultado.getString("observacoes"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return usuario;
	}

	@Override
	public void removeUsuario(int id) {
		String sql ="DELETE FROM usuario WHERE id=?";
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
	public boolean autenticarUsuario(Usuario usuario) {
		String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, usuario.getEmail());
			preparador.setString(2, usuario.getSenha());
			ResultSet resultado = preparador.executeQuery();
			return resultado.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
