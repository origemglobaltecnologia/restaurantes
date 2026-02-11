package br.com.administracao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.administracao.model.Usuario;
import br.com.administracao.service.UsuarioSERVICE;
import br.com.administracao.service.UsuarioSERVICEImpl;

/**
 * Servlet implementation class CardapioController
 */
@WebServlet("/usuariocontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static UsuarioSERVICEImpl usuarioSERVICEImpl = new UsuarioSERVICEImpl();
	UsuarioSERVICE usuarioService = usuarioSERVICEImpl;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
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

		if (acao != null && acao.equals("addUsuario")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/usuario/usuario.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("updateUsuario")) {
			Usuario usuario = this.usuarioService.getUsuarioById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/usuario/usuario.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("listUsuarios")) {
			List<Usuario> listUsuarios = this.usuarioService.listUsuarios();
			request.setAttribute("listUsuarios", listUsuarios);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/usuario/usuarios.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("removeUsuario")) {
			this.usuarioService.removeUsuario(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("usuariocontroller.do?acao=listUsuarios");
		}

		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/usuario/home.jsp");
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
		
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEndereco(request.getParameter("endereco"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTelefone(request.getParameter("telefone"));
		usuario.setObservacoes(request.getParameter("observacoes"));
		if (request.getParameter("id") != null) {
			usuario.setId(Integer.parseInt(request.getParameter("id")));
			this.usuarioService.updateUsuario(usuario);
			classeBootstrap = "alert alert-success";
			msg = "Atualizado com sucesso!";
		} else {
			this.usuarioService.addUsuario(usuario);
			classeBootstrap = "alert alert-success";
			msg = "Cadastrado com sucesso!";
		}
		request.setAttribute("classeBootstrap", classeBootstrap);
		request.setAttribute("msg", msg);
		response.sendRedirect("usuariocontroller.do?acao=listUsuarios");

	}

}
