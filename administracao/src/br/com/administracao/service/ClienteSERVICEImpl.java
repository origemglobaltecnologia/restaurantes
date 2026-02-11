package br.com.administracao.service;

import java.util.List;

import br.com.administracao.dao.ClienteDAO;
import br.com.administracao.dao.ClienteDAOImpl;
import br.com.administracao.model.Cliente;

public class ClienteSERVICEImpl implements ClienteSERVICE {

	static ClienteDAOImpl clienteDAOImpl = new ClienteDAOImpl();
	ClienteDAO clienteDao = clienteDAOImpl;
	
	@Override
	public void addCliente(Cliente c) {
		this.clienteDao.addCliente(c);
	}

	@Override
	public void updateCliente(Cliente c) {
		this.clienteDao.updateCliente(c);
	}

	@Override
	public List<Cliente> listClientes() {
		return this.clienteDao.listClientes();
	}

	@Override
	public Cliente getClienteById(int id) {
		return this.clienteDao.getClienteById(id);
	}

	@Override
	public void removeCliente(int id) {
		this.clienteDao.removeCliente(id);
	}

}
