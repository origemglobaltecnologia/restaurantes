package br.com.administracao.service;

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
import br.com.administracao.model.Caixa;
import br.com.administracao.model.Conta;
import br.com.administracao.model.Pedido;

public class ContaSERVICEImpl implements ContaSERVICE {

	static CaixaSERVICEImpl caixaSERVICEImpl = new CaixaSERVICEImpl();
	CaixaSERVICE caixaSERVICE = caixaSERVICEImpl;
	static ContaDAOImpl contaDAOImpl = new ContaDAOImpl();
	ContaDAO contaDao = contaDAOImpl;
	static PedidoSERVICEImpl pedidoSERVICEImpl = new PedidoSERVICEImpl();
	PedidoSERVICE pedidoService = pedidoSERVICEImpl;
	Calendar calendar = Calendar.getInstance();

	/*
	 * COMMONS METHODS
	 * 		-> CRUD
	 * 
	 */
	@Override
	public void addConta(Conta c) {
		c.setHora(calendar.get(Calendar.HOUR_OF_DAY));
		c.setMinuto(calendar.get(Calendar.MINUTE));
		c.setSegundo(calendar.get(Calendar.SECOND));
		this.contaDao.addConta(c);
	}

	@Override
	public Conta getContaById(int id) {
		return this.contaDao.getContaById(id);
	}
	
	@Override
	public void updateConta(Conta c) {
		c.setHora(calendar.get(Calendar.HOUR_OF_DAY));
		c.setMinuto(calendar.get(Calendar.MINUTE));
		c.setSegundo(calendar.get(Calendar.SECOND));
		this.contaDao.updateConta(c);
	}
	
	@Override
	public List<Conta> listContas() {
		return this.contaDao.listContas();
	}

	@Override
	public void removeConta(int id) {
		this.contaDao.removeConta(id);
	}
	
	// END - COMMONS METHODS

	/*
	 * BUSINNESS LAW
	 * 		-> OTHERS METHODS
	 */
	@Override
	public Conta getContaByLastId() {
		return this.contaDao.getContaByLastId();
	}
	
	@Override
	public void cancelConta(int idConta) {
		// Busca pedidos da conta
		List<Pedido> pedidosParaCancelar = this.pedidoService.listPedidosByConta(idConta);
		// Altera status de todos os itens para cancelado
		for(Pedido pedido : pedidosParaCancelar) {
			this.pedidoService.cancelPedidosByConta(pedido.getId());
		}
		// Busca a conta
		Conta conta = this.getContaById(idConta);
		// Seta status da conta CANCELADA
		conta.setStatus("CANCELADA");
		// Atualiza a conta
		this.contaDao.updateConta(conta);
		// Pega o valor da conta
		BigDecimal totalConta = conta.getTotal();
		// Busca o caixa
		Caixa caixa = caixaSERVICE.getCaixaByLastId();
		// Pega o valor do caixa
		BigDecimal valorCaixa = caixa.getValor();
		// Subtrai o valor da conta
		valorCaixa = valorCaixa.subtract(totalConta);
		// Seta o valor do caixa
		caixa.setValor(valorCaixa);
		// Atualiza o caixa
		this.caixaSERVICE.updateCaixa(caixa);
	}
	
	@Override
	public Conta getContaByMesa(int mesa) {
		return this.contaDao.getContaByMesa(mesa);
	}
	
	@Override
	public Conta getContaByMesaAberta(int mesa) {
		return this.contaDao.getContaByMesaAberta(mesa);
	}

	@Override
	public Conta getContaBySequencia(int sequencia) {
		return this.contaDao.getContaBySequencia(sequencia);
	}
	
	@Override
	public Conta getContaBySequenciaCaixaVigente() {
		return this.contaDao.getContaBySequenciaCaixaVigente();
	}
	
	@Override
	public List<Conta> listContasAbertas() {
		return this.contaDao.listContasAbertas();
	}

	@Override
	public List<Conta> listContasByLastCaixa() {
		return this.contaDao.listContasByLastCaixa();
	}
	
	@Override
	public List<Conta> listContasByCaixa(int caixa) {
		return this.contaDao.listContasByCaixa(caixa);
	}
	
	@Override
	public List<Conta> listContasFechadas() {
		return this.contaDao.listContasFechadas();
	}
	
	@Override
	public  HashMap<String, List<String>> getContaByIdJoinUsuarioClientePedidosItens(int id) {
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
		return (HashMap<String, List<String>>) listConta;
	}

	@Override
	public void subtrairValorDaConta(int idConta, BigDecimal valorItem, BigDecimal comissaoItem) {
		Conta conta = this.getContaById(idConta);
		BigDecimal valorConta = conta.getValor();
		BigDecimal valorParaGravar = valorConta.subtract(valorItem);
		conta.setValor(valorParaGravar);
		if(comissaoItem == null || comissaoItem.equals("0.00")){
			BigDecimal totalConta = conta.getTotal();
			BigDecimal totalContaParaGravar = totalConta.subtract(valorItem);
			conta.setTotal(totalContaParaGravar);
		} else {
			BigDecimal comissaoConta = conta.getComissao();
			BigDecimal comissaoParaGravar = comissaoConta.subtract(comissaoItem);
			conta.setComissao(comissaoParaGravar);
			BigDecimal totalConta = conta.getTotal();
			BigDecimal valorItemMaisComissao = valorItem.add(comissaoItem);
			BigDecimal totalContaParaGravar = totalConta.subtract(valorItemMaisComissao);
			conta.setTotal(totalContaParaGravar);
		}
		this.updateConta(conta);
	}
	// INTERNAL METHODS
	
}
