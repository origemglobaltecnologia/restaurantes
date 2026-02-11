package br.com.administracao.service;

import java.util.List;

import br.com.administracao.dao.UsuarioDAO;
import br.com.administracao.dao.UsuarioDAOImpl;
import br.com.administracao.model.Usuario;

public class UsuarioSERVICEImpl implements UsuarioSERVICE {

	static UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
	UsuarioDAO usuarioDao = usuarioDAOImpl;
	
	@Override
	public void addUsuario(Usuario c) {
		this.usuarioDao.addUsuario(c);
	}

	@Override
	public void updateUsuario(Usuario c) {
		this.usuarioDao.updateUsuario(c);
	}

	@Override
	public List<Usuario> listUsuarios() {
		return this.usuarioDao.listUsuarios();
	}

	@Override
	public Usuario getUsuarioById(int id) {
		return this.usuarioDao.getUsuarioById(id);
	}
	
	@Override
	public Usuario getUsuarioByEmail(String email) {
		return this.usuarioDao.getUsuarioByEmail(email);
	}

	@Override
	public void removeUsuario(int id) {
		this.usuarioDao.removeUsuario(id);
	}

	@Override
	public boolean autenticarUsuario(Usuario u) {
		return this.usuarioDao.autenticarUsuario(u);
	}

}
