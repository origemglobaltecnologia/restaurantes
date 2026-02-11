package br.com.administracao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.administracao.model.Cliente;
import br.com.administracao.util.ConectaMySQL;

public class ClienteDAOImpl implements ClienteDAO {
	private Connection conn = ConectaMySQL.getConnection();

	static CaixaDAOImpl caixaDAOImpl = new CaixaDAOImpl();
	
	@Override
	public void addCliente(Cliente c) {
		String sql = "INSERT INTO cliente(nome, cep, rua, numero, complemento, bairro, cidade, uf, email, celular, empresa, telefone, observacoes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, c.getNome());
			preparador.setString(2, c.getCep());
			preparador.setString(3, c.getRua());
			preparador.setString(4, c.getNumero());
			preparador.setString(5, c.getComplemento());
			preparador.setString(6, c.getBairro());
			preparador.setString(7, c.getCidade());
			preparador.setString(8, c.getUf());
			preparador.setString(9, c.getEmail());
			preparador.setString(10, c.getCelular());
			preparador.setString(11, c.getEmpresa());
			preparador.setString(12, c.getTelefone());
			preparador.setString(13, c.getObservacoes());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Algum erro ocorreu. Mensagem: " + e.getMessage());
		}				
	}

	@Override
	public void updateCliente(Cliente c) {
		String sql ="UPDATE cliente SET nome=?, cep=?, rua=?, numero=?, complemento=?, bairro=?, cidade=?, uf=?, email=?, celular=?, empresa=?, telefone=?, observacoes=? WHERE id=?";
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setString(1, c.getNome());
			preparador.setString(2, c.getCep());
			preparador.setString(3, c.getRua());
			preparador.setString(4, c.getNumero());
			preparador.setString(5, c.getComplemento());
			preparador.setString(6, c.getBairro());
			preparador.setString(7, c.getCidade());
			preparador.setString(8, c.getUf());
			preparador.setString(9, c.getEmail());
			preparador.setString(10, c.getCelular());
			preparador.setString(11, c.getEmpresa());
			preparador.setString(12, c.getTelefone());
			preparador.setString(13, c.getObservacoes());
			preparador.setInt(14, c.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar. Mensagem: " + e.getMessage());
		}				
	}

	@Override
	public List<Cliente> listClientes() {
		String sql = "SELECT * FROM cliente ORDER BY id DESC";
		List<Cliente> listClientes = new ArrayList<Cliente>();
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setCep(resultado.getString("cep"));
				cliente.setRua(resultado.getString("Rua"));
				cliente.setNumero(resultado.getString("numero"));
				cliente.setComplemento(resultado.getString("complemento"));
				cliente.setBairro(resultado.getString("bairro"));
				cliente.setCidade(resultado.getString("cidade"));
				cliente.setUf(resultado.getString("uf"));
				cliente.setEmail(resultado.getString("email"));
				cliente.setCelular(resultado.getString("celular"));
				cliente.setEmpresa(resultado.getString("empresa"));
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setObservacoes(resultado.getString("observacoes"));
				listClientes.add(cliente);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return listClientes;
	}

	@Override
	public Cliente getClienteById(int id) {
		String sql = "SELECT * FROM cliente WHERE id = ?";
		Cliente cliente = null;
		try {
			PreparedStatement preparador = (PreparedStatement) conn.prepareStatement(sql);
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				cliente = new Cliente();
				cliente.setId(resultado.getInt("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setCep(resultado.getString("cep"));
				cliente.setRua(resultado.getString("Rua"));
				cliente.setNumero(resultado.getString("numero"));
				cliente.setComplemento(resultado.getString("complemento"));
				cliente.setBairro(resultado.getString("bairro"));
				cliente.setCidade(resultado.getString("cidade"));
				cliente.setUf(resultado.getString("uf"));
				cliente.setEmail(resultado.getString("email"));
				cliente.setCelular(resultado.getString("celular"));
				cliente.setEmpresa(resultado.getString("empresa"));
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setObservacoes(resultado.getString("observacoes"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar. Mensagem: " + e.getMessage());
		}
		return cliente;
	}

	@Override
	public void removeCliente(int id) {
		String sql ="DELETE FROM cliente WHERE id=?";
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
