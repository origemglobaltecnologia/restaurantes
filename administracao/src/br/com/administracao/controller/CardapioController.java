package br.com.administracao.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.administracao.model.Cardapio;
import br.com.administracao.service.CardapioSERVICE;
import br.com.administracao.service.CardapioSERVICEImpl;

@WebServlet("/cardapiocontroller.do")
public class CardapioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static CardapioSERVICEImpl cardapioSERVICEImpl = new CardapioSERVICEImpl();
	CardapioSERVICE cardapioService = cardapioSERVICEImpl;
	
	public CardapioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("addCardapio")) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cardapio/cardapio.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("updateCardapio")) {
			Cardapio cardapio = this.cardapioService.getCardapioById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("cardapio", cardapio);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cardapio/cardapio.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("listCardapios")) {
			List<Cardapio> listCardapios = this.cardapioService.listCardapios();
			request.setAttribute("listCardapios", listCardapios);
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cardapio/cardapios.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("removeCardapio")) {
			this.cardapioService.removeCardapio(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("cardapiocontroller.do?acao=listCardapios");
		}

		if (acao == null) {
			RequestDispatcher saida = request.getRequestDispatcher("paginas/cardapio/home.jsp");
			saida.forward(request, response);
		}

		if (acao != null && acao.equals("listarjson")) {
			JSONArray listaCardapios = new JSONArray(); //cria o array de objetos no padrao JSON
			for (Cardapio c: cardapioService.listCardapios()){ //pegando os dados do DAO
				listaCardapios.put(new JSONObject(c)); //convertendo para JSONObject e adicionando ao JSONArray
			}
			response.setContentType("application/json");
			response.getWriter().write(listaCardapios.toString());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String classeBootstrap;
		String msg;
		Cardapio c = new Cardapio();
		
		c.setTitulo(request.getParameter("titulo"));
		c.setDescricao(request.getParameter("descricao"));
		c.setValor(new BigDecimal(request.getParameter("valor")));
		c.setSetor(request.getParameter("setor"));
		c.setStatus(request.getParameter("status"));
		if (request.getParameter("id") != null) {
			c.setId(Integer.parseInt(request.getParameter("id")));
			this.cardapioService.updateCardapio(c);
			classeBootstrap = "alert alert-success";
			msg = "Atualizado com sucesso!";
		} else {
			this.cardapioService.addCardapio(c);
			classeBootstrap = "alert alert-success";
			msg = "Cadastrado com sucesso!";
		}
		request.setAttribute("classeBootstrap", classeBootstrap);
		request.setAttribute("msg", msg);
		response.sendRedirect("cardapiocontroller.do?acao=listCardapios");

	}

}
