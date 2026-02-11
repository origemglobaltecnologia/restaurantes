package br.com.administracao.service;

import java.util.List;

import br.com.administracao.model.Cliente;

public interface ClienteSERVICE {

	public void addCliente(Cliente c);
	public void updateCliente(Cliente c);
	public List<Cliente> listClientes();
	public Cliente getClienteById(int id);
	public void removeCliente(int id);
	
}
