package br.com.administracao.test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.administracao.dao.ContaDAO;
import br.com.administracao.dao.ContaDAOImpl;
import br.com.administracao.model.Conta;

public class TestContaDAO {
	
	static ContaDAOImpl contaDAOImpl = new ContaDAOImpl();

	public static void main(String[] args) {
		testAddConta();
		testUpdateConta();
		testListContas();
		testListContasByCaixa(5);
		testGetContaById(1);
		testRemoveConta(1);
		
		testGetContaByIdJoinUsuarioClientePedidosItens(1);
		
		testGetContaBySequenciaCaixaVigente();
	}

	public static void testAddConta(){
		Conta conta = new Conta();
		conta.setCaixa(1);
		conta.setTipo("SALAO");
		conta.setMesa(3);
		conta.setSequencia(0);
		conta.setUsuario(1);
		conta.setValor(new BigDecimal(0.00));
		conta.setComissao(new BigDecimal(0.00));
		conta.setDesconto(new BigDecimal(0.00));
		conta.setTotal(new BigDecimal(0.00));
		conta.setPagamento("DINHEIRO");
		conta.setRecebido(new BigDecimal(0.00));
		conta.setTroco(new BigDecimal(0.00));
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		// Manipulando dados da hora
		conta.setHora(c.get(Calendar.HOUR_OF_DAY));
		conta.setMinuto(c.get(Calendar.MINUTE));
		conta.setSegundo(c.get(Calendar.SECOND));
		conta.setObservacao("Conta de testes!");
		conta.setStatus("ABERTA");
		// DAO INSERT
		ContaDAO contaDao = contaDAOImpl;
		contaDao.addConta(conta);
	}
	
	public static void testUpdateConta(){
		Conta conta = new Conta();
		conta.setId(1);
		conta.setCaixa(1);
		conta.setTipo("SALAO");
		conta.setMesa(3);
		conta.setSequencia(0);
		conta.setUsuario(1);
		// Valores
		conta.setValor(new BigDecimal(110.00));
		conta.setComissao(new BigDecimal(0.00));
		conta.setDesconto(new BigDecimal(0.00));
		conta.setTotal(new BigDecimal(0.00));
		conta.setPagamento("DINHEIRO");
		conta.setRecebido(new BigDecimal(0.00));
		conta.setTroco(new BigDecimal(0.00));
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		// Manipulando dados da hora
		conta.setHora(c.get(Calendar.HOUR_OF_DAY));
		conta.setMinuto(c.get(Calendar.MINUTE));
		conta.setSegundo(c.get(Calendar.SECOND));
		conta.setObservacao("Conta de testes!");
		conta.setStatus("ABERTA");
		// DAO INSERT
		ContaDAO contaDao = contaDAOImpl;
		contaDao.updateConta(conta);
	}
	
	private static void testListContas(){
		ContaDAO contaDao = contaDAOImpl;
		List<Conta> listContas = contaDao.listContas();
		for (Conta conta : listContas) {
			System.out.println(
					"\nId Conta: " + conta.getId() +
					"\nCaixa Conta: " + conta.getCaixa() +
					"\nTipo Conta: " + conta.getTipo() +
					"\nMesa Conta: " + conta.getMesa() +
					"\nSequencia Conta: " + conta.getSequencia() +
					"\nObservação Conta: " + conta.getObservacao() +
					"\nHora Conta: " + conta.getHora() +
					"\nMinuto Conta: " + conta.getMinuto() +
					"\nSegundo Conta: " + conta.getSegundo() + 
					"\nStatus Conta: " + conta.getStatus() +
					"\nUsuario Conta: " + conta.getUsuario() +
					"\nValor Conta: " + conta.getValor() +
					"\nComissao Conta: " + conta.getComissao() +
					"\nDesconto Conta: " + conta.getDesconto() +
					"\nTotal Conta: " + conta.getTotal() +
					"\nPagamento Conta: " + conta.getPagamento() +
					"\nRecebido Conta: " + conta.getRecebido() +
					"\nTroco Conta: " + conta.getTroco()
			);
		}
	}
	
	private static void testListContasByCaixa(int caixa){
		ContaDAO contaDao = contaDAOImpl;
		List<Conta> listContas = contaDao.listContasByCaixa(caixa);
		for (Conta conta : listContas) {
			System.out.println(
					"\nId Conta: " + conta.getId() +
					"\nCaixa Conta: " + conta.getCaixa() +
					"\nTipo Conta: " + conta.getTipo() +
					"\nMesa Conta: " + conta.getMesa() +
					"\nSequencia Conta: " + conta.getSequencia() +
					"\nObservação Conta: " + conta.getObservacao() +
					"\nHora Conta: " + conta.getHora() +
					"\nMinuto Conta: " + conta.getMinuto() +
					"\nSegundo Conta: " + conta.getSegundo() + 
					"\nStatus Conta: " + conta.getStatus() +
					"\nUsuario Conta: " + conta.getUsuario() +
					"\nValor Conta: " + conta.getValor() +
					"\nComissao Conta: " + conta.getComissao() +
					"\nDesconto Conta: " + conta.getDesconto() +
					"\nTotal Conta: " + conta.getTotal() +
					"\nPagamento Conta: " + conta.getPagamento() +
					"\nRecebido Conta: " + conta.getRecebido() +
					"\nTroco Conta: " + conta.getTroco()
			);
		}
	}
	
	private static void testGetContaById(int id){
		ContaDAO contaDao = contaDAOImpl;
		Conta conta = contaDao.getContaById(id);
		System.out.println(
				"\nId Conta: " + conta.getId() +
				"\nCaixa Conta: " + conta.getCaixa() +
				"\nTipo Conta: " + conta.getTipo() +
				"\nMesa Conta: " + conta.getMesa() +
				"\nSequencia Conta: " + conta.getSequencia() +
				"\nObservação Conta: " + conta.getObservacao() +
				"\nHora Conta: " + conta.getHora() +
				"\nMinuto Conta: " + conta.getMinuto() +
				"\nSegundo Conta: " + conta.getSegundo() + 
				"\nStatus Conta: " + conta.getStatus() +
				"\nUsuario Conta: " + conta.getUsuario() +
				"\nValor Conta: " + conta.getValor() +
				"\nComissao Conta: " + conta.getComissao() +
				"\nDesconto Conta: " + conta.getDesconto() +
				"\nTotal Conta: " + conta.getTotal() +
				"\nPagamento Conta: " + conta.getPagamento() +
				"\nRecebido Conta: " + conta.getRecebido() +
				"\nTroco Conta: " + conta.getTroco()
		);		
	}
	
	private static void testGetContaBySequenciaCaixaVigente(){
		ContaDAO contaDao = contaDAOImpl;
		Conta conta = contaDao.getContaBySequenciaCaixaVigente();
		System.out.println(
				"\nId Conta: " + conta.getId() +
				"\nCaixa Conta: " + conta.getCaixa() +
				"\nTipo Conta: " + conta.getTipo() +
				"\nMesa Conta: " + conta.getMesa() +
				"\nSequencia Conta: " + conta.getSequencia() +
				"\nObservação Conta: " + conta.getObservacao() +
				"\nHora Conta: " + conta.getHora() +
				"\nMinuto Conta: " + conta.getMinuto() +
				"\nSegundo Conta: " + conta.getSegundo() + 
				"\nStatus Conta: " + conta.getStatus() +
				"\nUsuario Conta: " + conta.getUsuario() +
				"\nValor Conta: " + conta.getValor() +
				"\nComissao Conta: " + conta.getComissao() +
				"\nDesconto Conta: " + conta.getDesconto() +
				"\nTotal Conta: " + conta.getTotal() +
				"\nPagamento Conta: " + conta.getPagamento() +
				"\nRecebido Conta: " + conta.getRecebido() +
				"\nTroco Conta: " + conta.getTroco()
		);		
	}
	
	public static void testRemoveConta(int id){
		ContaDAO contaDao = contaDAOImpl;
		contaDao.removeConta(id);
	}
	
	private static void testGetContaByIdJoinUsuarioClientePedidosItens(int id){
		ContaDAO contaDao = contaDAOImpl;
		ResultSet resultset = contaDao.getContaByIdJoinUsuarioClientePedidosItens(id);
		Map<String, List<String>> listConta = new HashMap<String, List<String>>();
		try {
			while (resultset.next()) {
				List<String> itemView = new ArrayList<String>();
				String caixa_id = Integer.toString(resultset.getInt(1));
				itemView.add(caixa_id);
				String caixa_mesa = Integer.toString(resultset.getInt(2));
				itemView.add(caixa_mesa);
				String caixa_sequencia = Integer.toString(resultset.getInt(3));
				itemView.add(caixa_sequencia);
				itemView.add(resultset.getBigDecimal(4).toString());
				itemView.add(resultset.getBigDecimal(5).toString());
				itemView.add(resultset.getBigDecimal(6).toString());
				itemView.add(resultset.getBigDecimal(7).toString());
				itemView.add(resultset.getBigDecimal(8).toString());
				itemView.add(resultset.getBigDecimal(9).toString());
				itemView.add(resultset.getString(10));
				String usuario_id = Integer.toString(resultset.getInt(11));
				itemView.add(usuario_id);
				itemView.add(resultset.getString(12));
				String pedido_id = Integer.toString(resultset.getInt(13));
				itemView.add(pedido_id);
				String pedido_cliente = Integer.toString(resultset.getInt(14));
				itemView.add(pedido_cliente);
				String cliente_id = Integer.toString(resultset.getInt(15));
				itemView.add(cliente_id);
				itemView.add(resultset.getString(16));
				itemView.add(resultset.getString(17));
				String cliente_numero_endereco = Integer.toString(resultset.getInt(18));
				itemView.add(cliente_numero_endereco);
				itemView.add(resultset.getString(19));
				String item_id = Integer.toString(resultset.getInt(20));
				itemView.add(item_id);
				String item_quantidade = Integer.toString(resultset.getInt(21));
				itemView.add(item_quantidade);
				itemView.add(resultset.getBigDecimal(22).toString());
				itemView.add(resultset.getString(23));
				itemView.add(resultset.getString(24));
				
				String chave = Integer.toString(resultset.getInt(1));
				listConta.put(chave, itemView);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(listConta);
	}
}