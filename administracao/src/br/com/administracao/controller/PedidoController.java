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

import br.com.administracao.model.Conta;
import br.com.administracao.model.Pedido;
import br.com.administracao.service.CardapioSERVICE;
import br.com.administracao.service.CardapioSERVICEImpl;
import br.com.administracao.service.ClienteSERVICE;
import br.com.administracao.service.ClienteSERVICEImpl;
import br.com.administracao.service.ContaSERVICE;
import br.com.administracao.service.ContaSERVICEImpl;
import br.com.administracao.service.ImpressaoSERVICE;
import br.com.administracao.service.ImpressaoSERVICEImpl;
import br.com.administracao.service.ItemSERVICE;
import br.com.administracao.service.ItemSERVICEImpl;
import br.com.administracao.service.PedidoSERVICE;
import br.com.administracao.service.PedidoSERVICEImpl;

/**
 * Servlet implementation class CardapioController
 */
@WebServlet("/pedidocontroller.do")
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static PedidoSERVICEImpl pedidoSERVICEImpl = new PedidoSERVICEImpl();
	PedidoSERVICE pedidoService = pedidoSERVICEImpl;
	static CardapioSERVICEImpl cardapioSERVICEImpl = new CardapioSERVICEImpl();
	CardapioSERVICE cardapioService = cardapioSERVICEImpl;
	static ItemSERVICEImpl itemSERVICEImpl = new ItemSERVICEImpl();
	ItemSERVICE itemService = itemSERVICEImpl;
	static ClienteSERVICEImpl clienteSERVICEImpl = new ClienteSERVICEImpl();
	ClienteSERVICE clienteSERVICE = clienteSERVICEImpl;
	static ContaSERVICEImpl contaSERVICEImpl = new ContaSERVICEImpl();
	ContaSERVICE contaService = contaSERVICEImpl;
	static ImpressaoSERVICEImpl impressaoSERVICEImpl = new ImpressaoSERVICEImpl();
	ImpressaoSERVICE impressaoSERVICE = impressaoSERVICEImpl;

	public PedidoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		/*
		 * COMMONS METHODS
		 * 		-> CRUD
		 */
		if (acao != null && acao.equals("addPedido")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/pedido.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("updatePedido")) {
			Pedido pedido = this.pedidoService.getPedidoById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("pedido", pedido);
			Map<String, List<String>> listItens = this.itemService.listItemsByPedido(pedido.getId());
			request.setAttribute("listItens", listItens);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/pedido.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("listPedidos")) {
			List<Pedido> listPedidos = this.pedidoService.listPedidosByLasCaixa();
			request.setAttribute("listPedidos", listPedidos);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/pedidos.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("removePedido")) {
			this.pedidoService.removePedido(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("pedidocontroller.do?acao=listPedidos");
		}
		// END COMMONS METHODS

		/*
		 * BUSINNESS RULES
		 * 		-> OTHERS METHODS
		 */
		if (acao != null && acao.equals("addPedidoSalao")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/salao.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("addPedidoDelivery")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/delivery.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("cancelPedido")) {
			// Pega o id do pedido
			int id = Integer.parseInt(request.getParameter("id"));
			// Cancela o pedido
			this.pedidoService.cancelPedido(id);
			// Retorna para o pedido
			response.sendRedirect("pedidocontroller.do?acao=updatePedido&id="+id);
		}
		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/home.jsp");
			saida.forward(request, response);
		}
		// END - BUSINNES RULES
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Seta um pedido para cadastro
		Pedido pedido = new Pedido();
		String tipo = request.getParameter("tipo");
		pedido.setTipo(tipo);
		pedido.setMesa(Integer.parseInt(request.getParameter("mesa")));
		pedido.setSequencia(Integer.parseInt(request.getParameter("sequencia")));
		if (request.getParameter("cliente").matches("")) {
			String classeBootstrap = "alert alert-warning";
			String msg = "Preciso ter um cliente!";
			// retorna para o do pedido
			request.setAttribute("classeBootstrap", classeBootstrap);
			request.setAttribute("msg", msg);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/delivery.jsp");
			saida.forward(request, response);
		} else {
			pedido.setCliente(Integer.parseInt(request.getParameter("cliente")));
		}
		pedido.setPessoas(Integer.parseInt(request.getParameter("pessoas")));
		pedido.setUsuario(Integer.parseInt(request.getParameter("usuario")));
		pedido.setValor(new BigDecimal(request.getParameter("valor")));
		pedido.setObservacoes(request.getParameter("observacoes"));

		// Seta os itens para o cadastro
		String itens[] = request.getParameterValues("itens");
		String quantidades[] = request.getParameterValues("quantidades");
		String observacao[] = request.getParameterValues("observacao");
		String titulos[] = request.getParameterValues("titulos");
		String valoresItens[] = request.getParameterValues("valoresItens");

		if (itens == null) {
			String classeBootstrap = "alert alert-warning";
			String msg = "Preciso escolher os itens!";
			// retorna para o do pedido
			request.setAttribute("classeBootstrap", classeBootstrap);
			request.setAttribute("msg", msg);
			if (pedido.getTipo().matches("SALAO")) {
				RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/salao.jsp");
				saida.forward(request, response);
			} 
			if (pedido.getTipo().matches("DELIVERY")) {
				RequestDispatcher saida = request.getRequestDispatcher("paginas/pedido/delivery.jsp");
				saida.forward(request, response);
			}
		} else {
		/**
		 * Percorre os arrays de itens, quantidades e observação dos arrays recebidos 
		 */
		String [][] itensparacadastro = new String[itens.length][5];
		for(int i = 0; i < itens.length; i++){
			for(int j = 0; j < quantidades.length; j++){
				for(int k = 0; k < observacao.length; k++){
					for(int l = 0; l < titulos.length; l++){
						for(int m = 0; m < valoresItens.length; m++){
							itensparacadastro[i][0] = itens[i];
							itensparacadastro[i][1] = quantidades[i];
							itensparacadastro[i][2] = observacao[i];
							itensparacadastro[i][3] = titulos[i];
							itensparacadastro[i][4] = valoresItens[i];
						}
					}
				}
			}
		}

		Conta conta = new Conta();
		conta.setPagamento(request.getParameter("pagamento"));
		conta.setRecebido(new BigDecimal(request.getParameter("recebido")));
		conta.setTroco(new BigDecimal(request.getParameter("troco")));
		
		// Lança o pedido
		this.pedidoService.lancarPedido(pedido, itensparacadastro, conta);
		
		if (tipo.matches("DELIVERY")) {
			Conta cont = this.contaService.getContaByLastId();
			this.impressaoSERVICE.imprimirConta(cont.getId().toString());
		}
		this.impressaoSERVICE.imprimirItensLastPedido();
		// Seta a mensagem de retorno
		String classeBootstrap = "alert alert-success";
		String msg = "Lançado com sucesso!";
		
		// retorna para o home dos pedidos
		request.setAttribute("classeBootstrap", classeBootstrap);
		request.setAttribute("msg", msg);
		RequestDispatcher home = request.getRequestDispatcher("paginas/pedido/home.jsp");
		home.forward(request, response);
		}
	}

}
