package br.com.administracao.test;

import java.util.List;

import br.com.administracao.dao.UsuarioDAO;
import br.com.administracao.dao.UsuarioDAOImpl;
import br.com.administracao.model.Usuario;

public class TestUsuarioDAO {

	static UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();

	public static void main(String[] args) {
		testAddUsuario();
		testUpdateUsuario();
		testListUsuarios();
		testGetUsuarioById(1);
		testRemoveUsuario(1);
	}

	public static void testAddUsuario(){
		Usuario usuario = new Usuario();
		usuario.setNome("nome");
		usuario.setEndereco("endereco");
		usuario.setTelefone("telefone");
		usuario.setNome("email");
		usuario.setEndereco("senha");
		usuario.setTelefone("observacoes");
		UsuarioDAO usuarioDao = usuarioDAOImpl;
		usuarioDao.addUsuario(usuario);
	}
	
	public static void testUpdateUsuario(){
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("nome");
		usuario.setEndereco("endereco");
		usuario.setTelefone("telefone");
		usuario.setNome("email");
		usuario.setEndereco("senha");
		usuario.setTelefone("observacoes");		
		UsuarioDAO usuarioDao = usuarioDAOImpl;
		usuarioDao.updateUsuario(usuario);
	}
	
	private static void testListUsuarios(){
		UsuarioDAO usuarioDao = usuarioDAOImpl;
		List<Usuario> listUsuarios = usuarioDao.listUsuarios();
		for (Usuario usuario : listUsuarios) {
			System.out.println(
					"\nId Usuario: " + usuario.getId() +
					"\nNome Usuario: " + usuario.getNome() +
					"\nEndereço Usuario: " + usuario.getEndereco() +
					"\nTelefone Usuario: " + usuario.getTelefone() + 
					"\nEmail Usuario: " + usuario.getEmail() +
					"\nSenha Usuario: " + usuario.getSenha() + 
					"\nObservações Usuario: " + usuario.getObservacoes()
			);
		}
	}
	
	private static void testGetUsuarioById(int id){
		UsuarioDAO usuarioDao = usuarioDAOImpl;
		Usuario usuario = usuarioDao.getUsuarioById(id);
		System.out.println(
				"\nId Usuario: " + usuario.getId() +
				"\nNome Usuario: " + usuario.getNome() +
				"\nEndereço Usuario: " + usuario.getEndereco() +
				"\nTelefone Usuario: " + usuario.getTelefone() + 
				"\nEmail Usuario: " + usuario.getEmail() +
				"\nSenha Usuario: " + usuario.getSenha() + 
				"\nObservações Usuario: " + usuario.getObservacoes()
		);		
	}
	
	public static void testRemoveUsuario(int id){
		UsuarioDAO usuarioDao = usuarioDAOImpl;
		usuarioDao.removeUsuario(id);
	}

}