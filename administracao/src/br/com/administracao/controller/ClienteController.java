package br.com.administracao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.administracao.model.Cliente;
import br.com.administracao.service.ClienteSERVICE;
import br.com.administracao.service.ClienteSERVICEImpl;

/**
 * Servlet implementation class CardapioController
 */
@WebServlet("/clientecontroller.do")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ClienteSERVICEImpl clienteSERVICEImpl = new ClienteSERVICEImpl();
	ClienteSERVICE clienteService = clienteSERVICEImpl;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("addCliente")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cliente/cliente.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("updateCliente")) {
			Cliente cliente = this.clienteService.getClienteById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("cliente", cliente);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cliente/cliente.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("listClientes")) {
			List<Cliente> listClientes = this.clienteService.listClientes();
			request.setAttribute("listClientes", listClientes);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cliente/clientes.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("removeCliente")) {
			this.clienteService.removeCliente(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("clientecontroller.do?acao=listClientes");
		}

		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cliente/home.jsp");
			saida.forward(request, response);
		}
		
		if (acao != null && acao.equals("listarjson")) {
			JSONArray listaClientes = new JSONArray(); //cria o array de objetos no padrao JSON
			for (Cliente c: clienteService.listClientes()){ //pegando os dados do DAO
				listaClientes.put(new JSONObject(c)); //convertendo para JSONObject e adicionando ao JSONArray
			}
			response.setContentType("application/json");
			response.getWriter().write(listaClientes.toString());
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
		
		Cliente cliente = new Cliente();
		cliente.setNome(request.getParameter("nome"));
		cliente.setCep(request.getParameter("cep"));
		cliente.setRua(request.getParameter("rua"));
		cliente.setNumero(request.getParameter("numero"));
		cliente.setComplemento(request.getParameter("complemento"));
		cliente.setBairro(request.getParameter("bairro"));
		cliente.setCidade(request.getParameter("cidade"));
		cliente.setUf(request.getParameter("uf"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setCelular(request.getParameter("celular"));
		cliente.setEmpresa(request.getParameter("empresa"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setObservacoes(request.getParameter("observacoes"));
		if (request.getParameter("id") != null) {
			cliente.setId(Integer.parseInt(request.getParameter("id")));
			this.clienteService.updateCliente(cliente);
			classeBootstrap = "alert alert-success";
			msg = "Atualizado com sucesso!";
		} else {
			this.clienteService.addCliente(cliente);
			classeBootstrap = "alert alert-success";
			msg = "Cadastrado com sucesso!";
		}
		request.setAttribute("classeBootstrap", classeBootstrap);
		request.setAttribute("msg", msg);
		RequestDispatcher saida = request.getRequestDispatcher("paginas/cliente/home.jsp");
		saida.forward(request, response);

	}

}
