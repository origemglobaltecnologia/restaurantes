package br.com.administracao.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.administracao.model.Item;
import br.com.administracao.service.CaixaSERVICE;
import br.com.administracao.service.CaixaSERVICEImpl;
import br.com.administracao.service.ContaSERVICE;
import br.com.administracao.service.ContaSERVICEImpl;
import br.com.administracao.service.ItemSERVICE;
import br.com.administracao.service.ItemSERVICEImpl;
import br.com.administracao.service.PedidoSERVICE;
import br.com.administracao.service.PedidoSERVICEImpl;

@WebServlet("/itemcontroller.do")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ItemSERVICEImpl itemSERVICEImpl = new ItemSERVICEImpl();
	ItemSERVICE itemService = itemSERVICEImpl;
	static PedidoSERVICEImpl pedidoSERVICEImpl = new PedidoSERVICEImpl();
	PedidoSERVICE pedidoSERVICE = pedidoSERVICEImpl;
	static CaixaSERVICEImpl caixaSERVICEImpl = new CaixaSERVICEImpl();
	CaixaSERVICE caixaSERVICE = caixaSERVICEImpl;
	static ContaSERVICEImpl contaSERVICEImpl = new ContaSERVICEImpl();
	ContaSERVICE contaSERVICE = contaSERVICEImpl;

	public ItemController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("addItem")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/item.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("updateItem")) {
			Item item = this.itemService.getItemById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("item", item);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/item.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("listItens")) {
			Map<String, List<String>> listItens = this.itemService.listItems();
			request.setAttribute("listItens", listItens);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/itens.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("pizzaria")) {
			Map<String, List<String>> listItens = this.itemService.listItensLeftJoinCardapioLeftJoinPedido("PIZZARIA");
			request.setAttribute("listItens", listItens);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/itens.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("cozinha")) {
			Map<String, List<String>> listItens = this.itemService.listItensLeftJoinCardapioLeftJoinPedido("COZINHA");
			request.setAttribute("listItens", listItens);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/itens.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("bar")) {
			Map<String, List<String>> listItens = this.itemService.listItensLeftJoinCardapioLeftJoinPedido("BAR");
			request.setAttribute("listItens", listItens);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/itens.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("removeItem")) {
			this.itemService.removeItem(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("itemcontroller.do?acao=listItens");
		}
		if (acao != null && acao.equals("cancelItem")) {
			// Pega o id do item
			int idItem = Integer.parseInt(request.getParameter("id"));
			// Busca o item pelo id
			Item item = itemService.getItemById(idItem);
			// Pega o id da conta que está contido item
			int idConta = item.getConta();
			// Pega o id do pedido em que está contido item
			int idPedido = item.getPedido();
			// Pega o id da caixa que está contido item
			int idCaixa = item.getCaixa();
			// Pega o tipo da conta que está contido item 
			String tipoItem = item.getTipo();
			// Pega o valor total do item
			BigDecimal valorItem = item.getValor();
			// Subtrai o valorItem do valorPedido
			pedidoSERVICE.subtrairValorDoPedido(idPedido, valorItem);
			BigDecimal comissaoItem = null;
			// Testa se tipo vem de SALAO, caso TRUE, soma a comissão ao valor
			if (tipoItem.matches("SALAO")) {
				comissaoItem = valorItem.multiply(new BigDecimal("0.10"));
			}
			// Subtrai o valorItem do valorConta 
			contaSERVICE.subtrairValorDaConta(idConta, valorItem, comissaoItem);
			// Subtrai o valorItem do valorCaixa 
			caixaSERVICE.subtrairValorDoCaixa(idCaixa, valorItem, comissaoItem);
			item.setStatus("CANCELADO");
			itemService.updateItem(item);
			// Seta a mensagem de retorno
			String classeBootstrap = "alert alert-success";
			String msg = "Item CANCELADO com sucesso!";
			
			// retorna para o home dos pedidos
			request.setAttribute("classeBootstrap", classeBootstrap);
			request.setAttribute("msg", msg);
			response.sendRedirect("pedidocontroller.do?acao=updatePedido&id=" + idPedido);
		}
		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/item/home.jsp");
			saida.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String classeBootstrap;
		String msg;
		Item item = new Item();
		item.setConta(Integer.parseInt(request.getParameter("conta")));
		item.setPedido(Integer.parseInt(request.getParameter("pedido")));
		item.setCardapio(Integer.parseInt(request.getParameter("cardapio")));
		item.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		item.setObservacao(request.getParameter("observacao"));
		item.setStatus(request.getParameter("status"));
		item.setValor(new BigDecimal(request.getParameter("valor")));
		if (!request.getParameter("id").isEmpty()) {
			item.setId(Integer.parseInt(request.getParameter("id")));
			this.itemService.updateItem(item);
			classeBootstrap = "alert alert-success";
			msg = "Atualizado com sucesso!";
		} else {
			this.itemService.addItem(item);
			classeBootstrap = "alert alert-success";
			msg = "Cadastrado com sucesso!";
		}
		request.setAttribute("classeBootstrap", classeBootstrap);
		request.setAttribute("msg", msg);
		RequestDispatcher saida = request.getRequestDispatcher("paginas/item/home.jsp");
		saida.forward(request, response);

	}

}
