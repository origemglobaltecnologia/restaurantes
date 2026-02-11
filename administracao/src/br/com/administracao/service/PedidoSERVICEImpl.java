package br.com.administracao.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import br.com.administracao.dao.PedidoDAO;
import br.com.administracao.dao.PedidoDAOImpl;
import br.com.administracao.model.Caixa;
import br.com.administracao.model.Conta;
import br.com.administracao.model.Item;
import br.com.administracao.model.Pedido;

public class PedidoSERVICEImpl implements PedidoSERVICE {

	static PedidoDAOImpl pedidoDAOImpl = new PedidoDAOImpl();
	PedidoDAO pedidoDao = pedidoDAOImpl;
	static ItemSERVICEImpl itemSERVICEImpl = new ItemSERVICEImpl();
	ItemSERVICE itemSERVICE = itemSERVICEImpl;
	static ContaSERVICEImpl contaSERVICEImpl = new ContaSERVICEImpl();
	ContaSERVICE contaSERVICE = contaSERVICEImpl;
	static CaixaSERVICEImpl caixaSERVICEImpl = new CaixaSERVICEImpl();
	CaixaSERVICE caixaSERVICE = caixaSERVICEImpl;

	Calendar calendar = Calendar.getInstance();

	/*
	 * COMMONS METHODS
	 * 		-> CRUD
	 * 
	 */
	@Override
	public void addPedido(Pedido p) {
		// Setando a hora do pedido
		p.setHora(calendar.get(Calendar.HOUR_OF_DAY));
		p.setMinuto(calendar.get(Calendar.MINUTE));
		p.setSegundo(calendar.get(Calendar.SECOND));
		// Acessando o dao para inserção do pedido
		this.pedidoDao.addPedido(p);
	}
	
	@Override
	public void updatePedido(Pedido p) {
		p.setHora(Calendar.HOUR_OF_DAY);
		p.setMinuto(Calendar.MINUTE);
		p.setSegundo(Calendar.SECOND);
		this.pedidoDao.updatePedido(p);
	}
	
	@Override
	public List<Pedido> listPedidos() {
		return this.pedidoDao.listPedidos();
	}
	
	@Override
	public Pedido getPedidoById(int id) {
		return this.pedidoDao.getPedidoById(id);
	}

	@Override
	public void removePedido(int id) {
		this.pedidoDao.removePedido(id);
	}
	// END - COMMONS METHODS

	/*
	 * BUSINNESS LAW
	 * 		-> OTHERS METHODS
	 */
	@Override
	public void lancarPedido(Pedido p, String[][] itens, Conta c) {

		// Verifica se vem do salão e abre a conta
		if (p.getTipo().matches("SALAO")) {
			Conta conta = null;
			if (this.contaSERVICE.getContaByMesaAberta(p.getMesa()) != null) {
				conta = this.contaSERVICE.getContaByMesaAberta(p.getMesa());
			} else {
				this.abrirConta(p);
				conta = this.contaSERVICE.getContaByLastId();
			}
			this.somarConta(conta, p);
			this.somarCaixa(p);
			p.setConta(conta.getId());
		}
		// Verifica se vem do Delivery e abre a conta
		if (p.getTipo().matches("DELIVERY")) {
			// Testa se existe conta, o ELSE usa somente a primeira vez do dia
			int sequencia = 1;
			Caixa caixa = caixaSERVICE.getCaixaByLastId();
			if(caixa.getSequencia() != null){
				sequencia = caixa.getSequencia() + 1;
			} 
			// Seta a sequencia no pedido (Busca, mais 1)
			p.setSequencia(sequencia);
			this.abrirConta(p);
			Conta conta = this.contaSERVICE.getContaByLastId();
			conta.setRecebido(c.getRecebido());
			conta.setTroco(c.getTroco());
			conta.setPagamento(c.getPagamento());
			this.contaSERVICE.updateConta(conta);
			this.somarConta(conta, p);
			this.somarCaixa(p);
			this.somarSequenciaCaixa(sequencia);
			p.setConta(conta.getId());
		}
		// Adiciona o pedido
		this.addPedido(p);
		// Busca o pedido inserido
		Pedido pedido = this.getPedidoByLastId();
		// Acessando o itemService para cadastrar os itens
		this.itemSERVICE.addItens(itens, pedido);

	}

	@Override
	public List<Pedido> listPedidosByLasCaixa() {
		return this.pedidoDao.listPedidosByLasCaixa();
	}
	
	@Override
	public Pedido getPedidoByLastId() {
		return this.pedidoDao.getPedidoByLastId();
	}
	
	@Override
	public void cancelPedido(int id) {
		// Busca o pedido
		Pedido pedido = this.getPedidoById(id);
		// Busca itens do pedido
		Map<String, List<String>> listItens = this.itemSERVICE.listItemsByPedido(id);
		// Altera status de todos os itens para cancelado
		for(Map.Entry<String, List<String>> item : listItens.entrySet()) {
		    Item itemInsert = itemSERVICE.getItemById(Integer.parseInt(item.getKey()));
		    itemInsert.setStatus("CANCELADO");
		    this.itemSERVICE.updateItem(itemInsert);
		}
		// Busca valor do pedido
		BigDecimal valorPedido = pedido.getValor();
		// Check se Tipo Salao
		BigDecimal comissaoPedido = null;
		if (pedido.getTipo().matches("DELIVERY")) {
			comissaoPedido = valorPedido.multiply(new BigDecimal("0.00"));
		}
		if (pedido.getTipo().matches("SALAO")) {
			comissaoPedido = valorPedido.multiply(new BigDecimal("0.10"));
		}
		valorPedido = valorPedido.add(comissaoPedido);
		// Busca a conta
		Conta conta = contaSERVICE.getContaById(pedido.getConta());
		// Busca o valor da conta
		BigDecimal valorConta = conta.getValor();
		// Subtrai o total da conta
		valorConta = valorConta.subtract(valorPedido);
		// Seta o novo valor da conta
		conta.setValor(valorConta);
		// Check se valor da conta == 0.00
		if (valorConta.equals("0.00")) {
			// Se não, Cancela a conta
			conta.setStatus("CANCELADA");
		}
		// Atualiza a conta
		contaSERVICE.updateConta(conta);
		// Busca o caixa
		Caixa caixa = caixaSERVICE.getCaixaById(conta.getCaixa());
		// Busca o valor do caixa
		BigDecimal valorCaixa = caixa.getValor();
		// Subtrai o valor do pedido do caixa
		valorCaixa = valorCaixa.subtract(valorPedido);
		// Seta valor do caixa
		caixa.setValor(valorCaixa);
		// Atualiza o caixa
		caixaSERVICE.updateCaixa(caixa);
		
	}
	
	@Override
	public void cancelPedidosByConta(int idPedido) {
		// Busca itens do pedido
		Map<String, List<String>> listItens = this.itemSERVICE.listItemsByPedido(idPedido);
		// Altera status de todos os itens para cancelado
		for(Map.Entry<String, List<String>> item : listItens.entrySet()) {
		    Item itemInsert = itemSERVICE.getItemById(Integer.parseInt(item.getKey()));
		    itemInsert.setStatus("CANCELADO");
		    this.itemSERVICE.updateItem(itemInsert);
		}
		
	}
	
	@Override
	public ResultSet listPedidosJOINUsuario() {
		return this.pedidoDao.listPedidosJOINUsuario();
	}
	
	@Override
	public List<Pedido> listPedidosByConta(int conta_id) {
		return this.pedidoDao.listPedidosByConta(conta_id);
	}
	
	@Override
	public Pedido getPedidoByConta(int conta) {
		return this.pedidoDao.getPedidoByConta(conta);
	}

	@Override
	public void subtrairValorDoPedido(int idPedido, BigDecimal valorItem) {
		Pedido pedido = this.getPedidoById(idPedido);
		BigDecimal valorPedido = pedido.getValor();
		BigDecimal resultadoParaGravar = valorPedido.subtract(valorItem);
		pedido.setValor(resultadoParaGravar);
		this.updatePedido(pedido);
	}
	// END - BUSINNESS LAW
	
	/*
	 * INTERNAL METHODS
	 * 		-> KEEP...
	 */
	public void somarSequenciaCaixa(int sequencia) {
		Caixa caixa = this.caixaSERVICE.getCaixaByLastId();
		caixa.setSequencia(sequencia);
		this.caixaSERVICE.updateCaixa(caixa);
	}

	public void somarConta(Conta conta, Pedido pedido) {
		// Entradas da conta
		BigDecimal valorConta = conta.getValor();
		BigDecimal comissaoConta = conta.getComissao();
		BigDecimal totalConta = conta.getTotal();
		// Cálculo de comiisão 10%
		BigDecimal valorPedido = pedido.getValor();
		BigDecimal comissaoPedido = null;
		if (conta.getTipo().matches("DELIVERY")) {
			comissaoPedido = valorPedido.multiply(new BigDecimal("0.00"));
		}
		if (conta.getTipo().matches("SALAO")) {
			comissaoPedido = valorPedido.multiply(new BigDecimal("0.10"));
		}
		// Calculo de atualização do valor
		BigDecimal comissao = comissaoConta.add(comissaoPedido);
		BigDecimal res = valorConta.add(valorPedido);
		BigDecimal total = totalConta.add(comissaoPedido);
		total = total.add(valorPedido);
		// Saidas
		conta.setComissao(comissao);
		conta.setValor(res);
		conta.setTotal(total);
		this.contaSERVICE.updateConta(conta);
	}

	public void somarCaixa(Pedido pedido) {
		Caixa caixa = this.caixaSERVICE.getCaixaByLastIdAberto();
		BigDecimal valorCaixa = caixa.getValor();
		BigDecimal valorPedido = pedido.getValor();
		BigDecimal comissaoPedido = null;
		valorCaixa = valorCaixa.add(valorPedido);
		if (pedido.getTipo().matches("SALAO")) {
			comissaoPedido = valorPedido.multiply(new BigDecimal("0.10"));
			valorCaixa = valorCaixa.add(comissaoPedido);
		} 
		caixa.setValor(valorCaixa);
		this.caixaSERVICE.updateCaixa(caixa);
	}

	public void abrirConta(Pedido p) {
		Caixa caixa = null;
		if (this.caixaSERVICE.getCaixaByLastIdAberto() != null) {
			caixa = this.caixaSERVICE.getCaixaByLastIdAberto();
			// Trata a conta
			Conta conta = new Conta();
			conta.setCaixa(caixa.getId());
			conta.setTipo(p.getTipo());
			conta.setMesa(p.getMesa());
			conta.setSequencia(p.getSequencia());
			conta.setUsuario(p.getUsuario());
			conta.setValor(new BigDecimal(0.00));
			conta.setComissao(new BigDecimal(0.00));
			conta.setDesconto(new BigDecimal(0.00));
			conta.setTotal(new BigDecimal(0.00));
			conta.setPagamento("");
			conta.setRecebido(new BigDecimal(0.00));
			conta.setTroco(new BigDecimal(0.00));
			conta.setObservacao("");
			conta.setStatus("ABERTA");
			this.contaSERVICE.addConta(conta);
		}
	}
	// INTERNAL METHODS

}
