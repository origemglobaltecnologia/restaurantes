package br.com.administracao.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.administracao.model.Usuario;
import br.com.administracao.service.PedidoSERVICE;
import br.com.administracao.service.PedidoSERVICEImpl;
import br.com.administracao.service.UsuarioSERVICE;
import br.com.administracao.service.UsuarioSERVICEImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/maincontroller.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static UsuarioSERVICEImpl usuarioSERVICEImpl = new UsuarioSERVICEImpl();
	UsuarioSERVICE usuarioService = usuarioSERVICEImpl;
	static PedidoSERVICEImpl pedidoSERVICEImpl = new PedidoSERVICEImpl();
	PedidoSERVICE pedidoService = pedidoSERVICEImpl;
	
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher saida = request.getRequestDispatcher("paginas/util/home.jsp");
		saida.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		if(usuarioService.autenticarUsuario(usuario) == true){
			RequestDispatcher saida = request.getRequestDispatcher("home.jsp");
			saida.forward(request, response);
		} else {
			response.sendRedirect("./");
		}
		
	}

}
