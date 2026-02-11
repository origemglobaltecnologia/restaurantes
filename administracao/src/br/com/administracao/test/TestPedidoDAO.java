package br.com.administracao.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.administracao.dao.PedidoDAO;
import br.com.administracao.dao.PedidoDAOImpl;
import br.com.administracao.model.Pedido;

public class TestPedidoDAO {

	static PedidoDAOImpl pedidoDAOImpl = new PedidoDAOImpl();
	

	public static void main(String[] args) {
		testAddPedido();
		testUpdatePedido();
		testListPedidos();
		testListPedidosByLasCaixa();
		testListPedidosByConta(4);
		testGetPedidoById(1);
		testRemovePedido(1);
		testJoin();
	}

	public static void testAddPedido(){
		Pedido pedido = new Pedido();
		pedido.setConta(1);
		pedido.setTipo("SALAO");
		pedido.setMesa(0);
		pedido.setPessoas(3);
		pedido.setSequencia(0);
		pedido.setCliente(0);
		pedido.setUsuario(1);
		// Valores
		pedido.setValor(new BigDecimal(110.00));
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		// Manipulando dados da hora
		pedido.setHora(c.get(Calendar.HOUR_OF_DAY));
		pedido.setMinuto(c.get(Calendar.MINUTE));
		pedido.setSegundo(c.get(Calendar.SECOND));
		pedido.setObservacoes("Pedido de testes!");
		// DAO INSERT
		PedidoDAO pedidoDao = pedidoDAOImpl;
		pedidoDao.addPedido(pedido);
	}
	
	public static void testUpdatePedido(){
		Pedido pedido = new Pedido();
		pedido.setId(1);
		pedido.setConta(1);
		pedido.setTipo("SALAO");
		pedido.setMesa(3);
		pedido.setPessoas(3);
		pedido.setSequencia(0);
		pedido.setCliente(0);
		pedido.setUsuario(1);
		// Valores
		pedido.setValor(new BigDecimal(110.00));
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		// Manipulando dados da hora
		pedido.setHora(c.get(Calendar.HOUR_OF_DAY));
		pedido.setMinuto(c.get(Calendar.MINUTE));
		pedido.setSegundo(c.get(Calendar.SECOND));
		pedido.setObservacoes("Pedido de testes!");
		// DAO UPDATE
		PedidoDAO pedidoDao = pedidoDAOImpl;
		pedidoDao.updatePedido(pedido);
	}
	
	private static void testListPedidos(){
		PedidoDAO pedidoDao = pedidoDAOImpl;
		List<Pedido> listPedidos = pedidoDao.listPedidos();
		for (Pedido pedido : listPedidos) {
			System.out.println(
					"\nId Pedido: " + pedido.getId() +
					"\nConta Pedido: " + pedido.getConta() +
					"\nObservações Pedido: " + pedido.getObservacoes() +
					"\nCliente Pedido: " + pedido.getCliente() +
					"\nTipo Pedido: " + pedido.getTipo() +
					"\nMesa Pedido: " + pedido.getMesa() +
					"\nSequencia Pedido: " + pedido.getSequencia() +
					"\nPessoas Pedido: " + pedido.getPessoas() +
					"\nHora Pedido: " + pedido.getHora() +
					"\nMinuto Pedido: " + pedido.getMinuto() +
					"\nSegundo Pedido: " + pedido.getSegundo() + 
					"\nUsuario Pedido: " + pedido.getUsuario() +
					"\nValor Pedido: " + pedido.getValor()
					);
		}
	}
	
	private static void testListPedidosByLasCaixa(){
		PedidoDAO pedidoDao = pedidoDAOImpl;
		List<Pedido> listPedidos = pedidoDao.listPedidosByLasCaixa();
		for (Pedido pedido : listPedidos) {
			System.out.println(
					"\nId Pedido: " + pedido.getId() +
					"\nConta Pedido: " + pedido.getConta() +
					"\nObservações Pedido: " + pedido.getObservacoes() +
					"\nCliente Pedido: " + pedido.getCliente() +
					"\nTipo Pedido: " + pedido.getTipo() +
					"\nMesa Pedido: " + pedido.getMesa() +
					"\nSequencia Pedido: " + pedido.getSequencia() +
					"\nPessoas Pedido: " + pedido.getPessoas() +
					"\nHora Pedido: " + pedido.getHora() +
					"\nMinuto Pedido: " + pedido.getMinuto() +
					"\nSegundo Pedido: " + pedido.getSegundo() + 
					"\nUsuario Pedido: " + pedido.getUsuario() +
					"\nValor Pedido: " + pedido.getValor()
					);
		}
	}
	
	private static void testListPedidosByConta(int conta){
		PedidoDAO pedidoDao = pedidoDAOImpl;
		List<Pedido> listPedidos = pedidoDao.listPedidosByConta(conta);
		for (Pedido pedido : listPedidos) {
			System.out.println(
					"\nId Pedido: " + pedido.getId() +
					"\nConta Pedido: " + pedido.getConta() +
					"\nObservações Pedido: " + pedido.getObservacoes() +
					"\nCliente Pedido: " + pedido.getCliente() +
					"\nTipo Pedido: " + pedido.getTipo() +
					"\nMesa Pedido: " + pedido.getMesa() +
					"\nSequencia Pedido: " + pedido.getSequencia() +
					"\nPessoas Pedido: " + pedido.getPessoas() +
					"\nHora Pedido: " + pedido.getHora() +
					"\nMinuto Pedido: " + pedido.getMinuto() +
					"\nSegundo Pedido: " + pedido.getSegundo() + 
					"\nUsuario Pedido: " + pedido.getUsuario() +
					"\nValor Pedido: " + pedido.getValor()
					);
		}
	}
	
	private static void testGetPedidoById(int id){
		PedidoDAO pedidoDao = pedidoDAOImpl;
		Pedido pedido = pedidoDao.getPedidoById(id);
		System.out.println(
				"\nId Pedido: " + pedido.getId() +
				"\nConta Pedido: " + pedido.getConta() +
				"\nObservações Pedido: " + pedido.getObservacoes() +
				"\nCliente Pedido: " + pedido.getCliente() +
				"\nTipo Pedido: " + pedido.getTipo() +
				"\nMesa Pedido: " + pedido.getMesa() +
				"\nSequencia Pedido: " + pedido.getSequencia() +
				"\nPessoas Pedido: " + pedido.getPessoas() +
				"\nHora Pedido: " + pedido.getHora() +
				"\nMinuto Pedido: " + pedido.getMinuto() +
				"\nSegundo Pedido: " + pedido.getSegundo() + 
				"\nUsuario Pedido: " + pedido.getUsuario() +
				"\nValor Pedido: " + pedido.getValor()
		);		
	}
	
	public static void testRemovePedido(int id){
		PedidoDAO pedidoDao = pedidoDAOImpl;
		pedidoDao.removePedido(id);
	}
	
	public static void testJoin(){
		PedidoDAO pedidoDao = pedidoDAOImpl;
		ResultSet resultset = pedidoDao.listPedidosJOINUsuario();
		try {
			while (resultset.next()) {
				System.out.println(
						"\nTipo Pedido: " + resultset.getString(1) +
						"\nMesa Pedido: " + resultset.getInt(2) +
						"\nSequencia Pedido: " + resultset.getInt(3) +
						"\nCliente Pedido: " + resultset.getString(4) +
						
						"\nUsuario Pedido: " + resultset.getString(5) +
						"\nPessoas Pedido: " + resultset.getInt(6) +
						"\nValor Pedido: " + resultset.getBigDecimal(7) +
						"\nHora Pedido: " + resultset.getInt(8) +
						
						"\nMinuto Pedido: " + resultset.getInt(9) +
						"\nSegundo Pedido: " + resultset.getInt(10) +
						"\nConta Pedido: " + resultset.getInt(11) +
						"\nObservações Pedido: " + resultset.getString(12) +
						
						"\nItem Pedido: " + resultset.getString(13)
						
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
