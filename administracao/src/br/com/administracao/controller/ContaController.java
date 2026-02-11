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

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.administracao.model.Cliente;
import br.com.administracao.model.Conta;
import br.com.administracao.model.Pedido;
import br.com.administracao.model.Usuario;
import br.com.administracao.service.ClienteSERVICE;
import br.com.administracao.service.ClienteSERVICEImpl;
import br.com.administracao.service.ContaSERVICE;
import br.com.administracao.service.ContaSERVICEImpl;
import br.com.administracao.service.ItemSERVICE;
import br.com.administracao.service.ItemSERVICEImpl;
import br.com.administracao.service.PedidoSERVICE;
import br.com.administracao.service.PedidoSERVICEImpl;
import br.com.administracao.service.UsuarioSERVICE;
import br.com.administracao.service.UsuarioSERVICEImpl;

@WebServlet("/contacontroller.do")
public class ContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ContaSERVICEImpl contaSERVICEImpl = new ContaSERVICEImpl();
	ContaSERVICE contaService = contaSERVICEImpl;
	static UsuarioSERVICEImpl usuarioSERVICEImpl = new UsuarioSERVICEImpl();
	UsuarioSERVICE usuarioService = usuarioSERVICEImpl;
	static PedidoSERVICEImpl pedidoSERVICEImpl = new PedidoSERVICEImpl();
	PedidoSERVICE pedidoService = pedidoSERVICEImpl;
	static ClienteSERVICEImpl clienteSERVICEImpl = new ClienteSERVICEImpl();
	ClienteSERVICE clienteService = clienteSERVICEImpl;
	static ItemSERVICEImpl itemSERVICEImpl = new ItemSERVICEImpl();
	ItemSERVICE itemService = itemSERVICEImpl;

	public ContaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		/*
		 * COMMONS METHODS
		 * 		-> CRUD
		 */
		if (acao != null && acao.equals("addConta")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/conta.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("updateConta")) {
			int idConta = Integer.parseInt(request.getParameter("id"));
			Conta conta = this.contaService.getContaById(idConta);
			request.setAttribute("conta", conta);
			List<Pedido> listPedidos = this.pedidoService.listPedidosByConta(idConta);
			request.setAttribute("listPedidos", listPedidos);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/conta.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("listContas")) {
			List<Conta> listContas = this.contaService.listContasByLastCaixa();
			request.setAttribute("listContas", listContas);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/contas.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("removeConta")) {
			this.contaService.removeConta(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("contacontroller.do?acao=listContas");
		}
		if (acao != null && acao.equals("listConta")) {
			Map<String, List<String>> listConta = this.contaService.getContaByIdJoinUsuarioClientePedidosItens(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("listConta", listConta);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/conta-main.jsp");
			saida.forward(request, response);
		}
		// END COMMONS METHODS

		/*
		 * BUSINNESS RULES
		 * 		-> OTHERS METHODS
		 */
		if (acao != null && acao.equals("fechar")) {
			List<Conta> listContas = this.contaService.listContasAbertas();
			request.setAttribute("listContas", listContas);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/contas-main.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("fecharConta")) {
			int idConta = Integer.parseInt(request.getParameter("id")); 
			Conta conta = this.contaService.getContaById(idConta);
			request.setAttribute("conta", conta);
			Usuario usuario = this.usuarioService.getUsuarioById(conta.getUsuario());
			request.setAttribute("usuario", usuario);
			if(conta.getTipo().matches("DELIVERY")){
				Pedido pedido = this.pedidoService.getPedidoByConta(idConta);
				request.setAttribute("pedido", pedido);
				Cliente cliente = this.clienteService.getClienteById(pedido.getCliente());
				request.setAttribute("cliente", cliente);
			}
			if(conta.getTipo().matches("SALAO")){
				List<Pedido> listPedidos = this.pedidoService.listPedidosByConta(idConta);
				request.setAttribute("listPedidos", listPedidos);
			}
			Map<String, List<String>> listItens = this.itemService.listItemsByConta(idConta);
			request.setAttribute("listItens", listItens);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/conta-main.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("receber")) {
			List<Conta> listContas = this.contaService.listContasFechadas();
			request.setAttribute("listContas", listContas);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/contas-main.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("cancelConta")) {
			// Pega o id do pedido
			int id = Integer.parseInt(request.getParameter("id"));
			// Cancela a conta
			this.contaService.cancelConta(id);
			// Retorna para a conta
			response.sendRedirect("contacontroller.do?acao=updateConta&id="+id);
		}
		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/conta/home.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("listarjson")) {
			String id = request.getParameter("id");
			JSONArray conta = new JSONArray(); //cria o array de objetos no padrao JSON
			Conta c = this.contaService.getContaById(Integer.parseInt(id));
			conta.put(new JSONObject(c)); //convertendo para JSONObject e adicionando ao JSONArray
			response.setContentType("application/json");
			response.getWriter().write(conta.toString());
		}
		// END - BUSINNES RULES
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String classeBootstrap;
		String msg;
		Conta conta = new Conta();
		conta.setCaixa(Integer.parseInt(request.getParameter("caixa")));
		conta.setTipo(request.getParameter("tipo"));
		if(request.getParameter("mesa") != null){
			conta.setMesa(Integer.parseInt(request.getParameter("mesa")));
		}
		if(request.getParameter("sequencia") != null){
			conta.setSequencia(Integer.parseInt(request.getParameter("sequencia")));
		}
		conta.setUsuario(Integer.parseInt(request.getParameter("usuario")));
		conta.setValor(new BigDecimal(request.getParameter("valor")));
		conta.setComissao(new BigDecimal(request.getParameter("comissao")));
		conta.setDesconto(new BigDecimal(request.getParameter("desconto")));
		conta.setTotal(new BigDecimal(request.getParameter("total")));
		conta.setPagamento(request.getParameter("pagamento"));
		conta.setRecebido(new BigDecimal(request.getParameter("recebido")));
		conta.setTroco(new BigDecimal(request.getParameter("troco")));
		conta.setObservacao(request.getParameter("observacao"));
		conta.setStatus(request.getParameter("status"));
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			conta.setId(id);
			this.contaService.updateConta(conta);
			classeBootstrap = "alert alert-success";
			msg = "Atualizado com sucesso!";
		} else {
			this.contaService.addConta(conta);
			Conta idConta = this.contaService.getContaByLastId();
			id = idConta.getId();
			classeBootstrap = "alert alert-success";
			msg = "Cadastrado com sucesso!";
		}
		request.setAttribute("classeBootstrap", classeBootstrap);
		request.setAttribute("msg", msg);
		response.sendRedirect("contacontroller.do?acao=fecharConta&id="+id);

	}

}
