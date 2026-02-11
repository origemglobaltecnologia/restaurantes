package br.com.administracao.test;

import java.util.List;

import br.com.administracao.dao.ClienteDAO;
import br.com.administracao.dao.ClienteDAOImpl;
import br.com.administracao.model.Cliente;

public class TestClienteDAO {

	static ClienteDAOImpl clienteDAOImpl = new ClienteDAOImpl();

	public static void main(String[] args) {
		testAddCliente();
		testUpdateCliente();
		testListClientes();
		testGetClienteById(4);
		testRemoveCliente(4);
	}

	public static void testAddCliente(){
		Cliente cliente = new Cliente();
		cliente.setNome("nome");
		cliente.setCelular("celular");
		cliente.setEmpresa("empresa");
		cliente.setTelefone("telefone");
		ClienteDAO clienteDao = clienteDAOImpl;
		clienteDao.addCliente(cliente);
	}
	
	public static void testUpdateCliente(){
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNome("nome");
		cliente.setCelular("celular");
		cliente.setEmpresa("empresa");
		cliente.setTelefone("telefone");
		ClienteDAO clienteDao = clienteDAOImpl;
		clienteDao.updateCliente(cliente);
	}
	
	private static void testListClientes(){
		ClienteDAO clienteDao = clienteDAOImpl;
		List<Cliente> listClientes = clienteDao.listClientes();
		for (Cliente cliente : listClientes) {
			System.out.println(
					"\nId Cliente: " + cliente.getId() +
					"\nNome Cliente: " + cliente.getNome() +
					"\nCelular Cliente: " + cliente.getCelular() +
					"\nEmpresa Cliente: " + cliente.getEmpresa() +
					"\nTelefone Cliente: " + cliente.getTelefone()
			);
		}
	}
	
	private static void testGetClienteById(int id){
		ClienteDAO clienteDao = clienteDAOImpl;
		Cliente cliente = clienteDao.getClienteById(id);
		System.out.println(
				"\nId Cliente: " + cliente.getId() +
				"\nNome Cliente: " + cliente.getNome() +
				"\nCelular Cliente: " + cliente.getCelular() +
				"\nEmpresa Cliente: " + cliente.getEmpresa() +
				"\nTelefone Cliente: " + cliente.getTelefone()
		);		
	}
	
	public static void testRemoveCliente(int id){
		ClienteDAO clienteDao = clienteDAOImpl;
		clienteDao.removeCliente(id);
	}

}