package br.com.administracao.service;

import java.util.List;

import br.com.administracao.model.Usuario;

public interface UsuarioSERVICE {

	public void addUsuario(Usuario u);
	public void updateUsuario(Usuario u);
	public List<Usuario> listUsuarios();
	public Usuario getUsuarioById(int id);
	public Usuario getUsuarioByEmail(String email);
	public void removeUsuario(int id);
	
	public boolean autenticarUsuario(Usuario u);
	
}
