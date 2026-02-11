package br.com.administracao.dao;


import java.util.List;

import br.com.administracao.model.Usuario;

public interface UsuarioDAO {

	public void addUsuario(Usuario usuario);
	public void updateUsuario(Usuario usuario);
	public List<Usuario> listUsuarios();
	public Usuario getUsuarioById(int id);	
	public Usuario getUsuarioByEmail(String email);	
	public void removeUsuario(int id);
	
	public boolean autenticarUsuario(Usuario usuario);
	
}
