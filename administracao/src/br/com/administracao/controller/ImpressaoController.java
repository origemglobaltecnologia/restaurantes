package br.com.administracao.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.administracao.service.ImpressaoSERVICE;
import br.com.administracao.service.ImpressaoSERVICEImpl;

@WebServlet("/impressaocontroller.do")
public class ImpressaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static ImpressaoSERVICEImpl impressaoSERVICEImpl = new ImpressaoSERVICEImpl();
	ImpressaoSERVICE impressaoSERVICE = impressaoSERVICEImpl;
	
    public ImpressaoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("imprimirCaixa")) {
			this.impressaoSERVICE.imprimirCaixa(request.getParameter("id"));
			this.impressaoSERVICE.imprimirContasByCaixa(request.getParameter("id"));
			this.impressaoSERVICE.imprimirItensByCaixa(request.getParameter("id"));
			//PrintWriter saida = response.getWriter();
			//saida.println("Confira a Impressora");
			response.sendRedirect("caixacontroller.do?acao=listCaixas");
		}
		if (acao != null && acao.equals("imprimirCaixaDiario")) {
			this.impressaoSERVICE.fechamentoDoCaixaDiario();
			response.sendRedirect("maincontroller.do");
		}
		if (acao != null && acao.equals("imprimirConta")) {
			int id = Integer.parseInt(request.getParameter("id"));
			this.impressaoSERVICE.imprimirConta(request.getParameter("id"));
			response.sendRedirect("contacontroller.do?acao=fecharConta&id="+id);
		}
		if (acao != null && acao.equals("imprimirPedido")) {
			response.sendRedirect("maincontroller.do");
		}
		if (acao != null && acao.equals("imprimirItens")) {
			this.impressaoSERVICE.imprimirItensLastPedido();
			response.sendRedirect("maincontroller.do");
		}
			
	}

}
