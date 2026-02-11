package br.com.administracao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.administracao.model.Caixa;
import br.com.administracao.model.Conta;
import br.com.administracao.model.Usuario;
import br.com.administracao.service.CaixaSERVICE;
import br.com.administracao.service.CaixaSERVICEImpl;
import br.com.administracao.service.ContaSERVICE;
import br.com.administracao.service.ContaSERVICEImpl;
import br.com.administracao.service.UsuarioSERVICE;
import br.com.administracao.service.UsuarioSERVICEImpl;

@WebServlet("/caixacontroller.do")
public class CaixaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CaixaSERVICEImpl caixaSERVICEImpl = new CaixaSERVICEImpl();
	CaixaSERVICE caixaService = caixaSERVICEImpl;
	static ContaSERVICEImpl contaSERVICEImpl = new ContaSERVICEImpl();
	ContaSERVICE contaService = contaSERVICEImpl;
	static UsuarioSERVICEImpl usuarioSERVICEImpl = new UsuarioSERVICEImpl();
	UsuarioSERVICE usuarioService = usuarioSERVICEImpl;

	String classeBootstrap = null;
	String msg = null;
	
	public CaixaController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Pega a ação a ser executada no método
		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equals("caixa")) {
			Caixa caixa = this.caixaService.getCaixaByLastId();
			request.setAttribute("caixa", caixa);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/fechar.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("addCaixa")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/autenticar.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("updateCaixa")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Caixa caixa = this.caixaService.getCaixaById(id);
			request.setAttribute("caixa", caixa);
			List<Conta> listContas = this.contaService.listContasByCaixa(id);
			request.setAttribute("listContas", listContas);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/caixa.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("listCaixas")) {
			List<Caixa> listCaixas = this.caixaService.listCaixas();
			request.setAttribute("listCaixas", listCaixas);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/caixas.jsp");
			saida.forward(request, response);
		}
		if (acao != null && acao.equals("cancelCaixa")) {
			PrintWriter saida = response.getWriter();
			saida.println("Implementando a função: A mesma precisa cancelar as contas, os pedidos e os itens. Basta apenas alterar o Status para CANCELADO");
		}
		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/home.jsp");
			saida.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pega a ação a ser executada no método
		String acao = request.getParameter("acao");

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		// Inicia as variáveis para mensagens de retorno: variavel + classe bootstrap
		String classeBootstrap = null;
		String msg = null;
				
		if (acao != null && acao.equals("autenticar")) {
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			if(usuarioService.autenticarUsuario(usuario)){
				Caixa caixa = new Caixa();
				caixa.setUsuario(Integer.parseInt(request.getParameter("usuario")));
				if (request.getParameter("id") != null) {
					caixa.setStatus(request.getParameter("status"));
					caixa.setValor(new BigDecimal(request.getParameter("valor")));
					caixa.setId(Integer.parseInt(request.getParameter("id")));
					this.caixaService.updateCaixa(caixa);
					classeBootstrap = "alert alert-success";
					msg = "Atualizado com sucesso!";
				} else {
					caixa.setStatus("ABERTO");
					caixa.setValor(new BigDecimal("0.00"));
					if(this.caixaService.addCaixa(caixa)){
						classeBootstrap = "alert alert-success";
						msg = "Iniciado com sucesso!";
					} else {
						classeBootstrap = "alert alert-danger";
						msg = "Há um caixa aberto!";
					}
				}	
				request.setAttribute("classeBootstrap", classeBootstrap);
				request.setAttribute("msg", msg);
				RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/home.jsp");
				saida.forward(request, response);	
			} else {
				classeBootstrap = "alert alert-danger";
				msg = "Erro com as credenciais!";
				request.setAttribute("classeBootstrap", classeBootstrap);
				request.setAttribute("msg", msg);
				RequestDispatcher saida = request.getRequestDispatcher("paginas/home.jsp");
				saida.forward(request, response);	
			}
		}	
		
		if (acao != null && acao.equals("fechar")) {
			if(!this.contaService.listContasAbertas().isEmpty() || !this.contaService.listContasFechadas().isEmpty()){
				msg = "Existem contas pendentes!";
				classeBootstrap = "alert alert-danger";
				request.setAttribute("msg", msg);
				request.setAttribute("classeBootstrap", classeBootstrap);
				RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/home.jsp");
				saida.forward(request, response);
			} else {
				Caixa caixa = this.caixaService.getCaixaByLastIdAberto();
				caixa.setStatus("FECHADO");
				this.caixaService.updateCaixa(caixa);
				classeBootstrap = "alert alert-success";
				msg = "Caixa fechado com sucesso!";
				request.setAttribute("msg", msg);
				request.setAttribute("classeBootstrap", classeBootstrap);
				RequestDispatcher saida = request.getRequestDispatcher("paginas/caixa/home.jsp");
				saida.forward(request, response);
			}
		}		

		if (acao != null && acao.equals("caixa")) {
			Caixa caixa = new Caixa();
			caixa.setUsuario(Integer.parseInt(request.getParameter("usuario")));
			if (request.getParameter("id") != null) {
				caixa.setStatus(request.getParameter("status"));
				caixa.setValor(new BigDecimal(request.getParameter("valor")));
				caixa.setId(Integer.parseInt(request.getParameter("id")));
				this.caixaService.updateCaixa(caixa);
				classeBootstrap = "alert alert-success";
				msg = "Atualizado com sucesso!";
			} else {
				caixa.setStatus("ABERTO");
				caixa.setValor(new BigDecimal("0.00"));
				if(this.caixaService.addCaixa(caixa)){
					classeBootstrap = "alert alert-success";
					msg = "Iniciado com sucesso!";
				} else {
					classeBootstrap = "alert alert-danger";
					msg = "Há um caixa aberto!";
				}
			}	

		}
	}

}
