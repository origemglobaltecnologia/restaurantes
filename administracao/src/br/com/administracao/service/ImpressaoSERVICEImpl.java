package br.com.administracao.service;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.administracao.impressora.ImpressoraBar;
import br.com.administracao.impressora.ImpressoraBarImpl;
import br.com.administracao.impressora.ImpressoraCaixa;
import br.com.administracao.impressora.ImpressoraCaixaImpl;
import br.com.administracao.impressora.ImpressoraCozinha;
import br.com.administracao.impressora.ImpressoraCozinhaImpl;
import br.com.administracao.impressora.ImpressoraPizzaria;
import br.com.administracao.impressora.ImpressoraPizzariaImpl;
import br.com.administracao.model.Caixa;
import br.com.administracao.model.Cliente;
import br.com.administracao.model.Conta;
import br.com.administracao.model.Item;
import br.com.administracao.model.Pedido;
import br.com.administracao.model.Usuario;

public class ImpressaoSERVICEImpl implements ImpressaoSERVICE {

	/**
	 * IMPORTS de classes
	 * 
	 */
	static CaixaSERVICEImpl caixaSERVICEImpl = new CaixaSERVICEImpl();
	CaixaSERVICE caixaService = caixaSERVICEImpl;
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
	Calendar calendar = Calendar.getInstance();
	
	static ImpressoraCaixaImpl impressoraCaixaImpl = new ImpressoraCaixaImpl();
	ImpressoraCaixa impressoraCaixa = impressoraCaixaImpl;
	static ImpressoraBarImpl impressoraBarImpl = new ImpressoraBarImpl();
	ImpressoraBar impressoraBar = impressoraBarImpl;
	static ImpressoraCozinhaImpl impressoraCozinhaImpl = new ImpressoraCozinhaImpl();
	ImpressoraCozinha impressoraCozinha = impressoraCozinhaImpl;
	static ImpressoraPizzariaImpl impressoraPizzariaImpl = new ImpressoraPizzariaImpl();
	ImpressoraPizzaria impressoraPizzaria = impressoraPizzariaImpl;
	// FIM Imports de classes 
	
	
	@Override
	public void imprimirItem(Item item) {
	
	}

	@Override
	public void imprimirItensByCaixa(String idCaixa) {
		HashMap<String, List<String>> itens = itemService.listItemsByCaixa(idCaixa);
		String	imprimir = "";
		String	itensImprimir = "";
		String	canceladosImprimir = "";
		for(Map.Entry<String, List<String>> entry : itens.entrySet()) {
			if(!entry.getValue().get(9).matches("CANCELADO")){
				itensImprimir = itensImprimir + entry.getKey() + " -> ";
				itensImprimir = itensImprimir + entry.getValue().get(4) + " - ";
				itensImprimir = itensImprimir + entry.getValue().get(7) + " - ";
				itensImprimir = itensImprimir + entry.getValue().get(8) + "\n";
			} else {
				canceladosImprimir = canceladosImprimir + entry.getKey() + " -> ";
				canceladosImprimir = canceladosImprimir + entry.getValue().get(4) + " - ";
				canceladosImprimir = canceladosImprimir + entry.getValue().get(7) + " - ";
				canceladosImprimir = canceladosImprimir + entry.getValue().get(8) + "\n";
			}
		}
		imprimir = "ITENS VENDIDOS: " +	itensImprimir + "\n\n" + 
				   "ITENS CANCELADOS: " + canceladosImprimir
                + "\n\n\n\n\n\n\n\n"
				;
		
		this.impressoraCaixa.imprimir(imprimir);
	}
	
	@Override
	public void imprimirPedido(Pedido pedido) {
		// Pedido
	}
	
	@Override
	public void imprimirConta(String idconta) {
		
		int idConta = Integer.parseInt(idconta); 
		Conta conta = this.contaService.getContaById(idConta);
		Usuario usuario = this.usuarioService.getUsuarioById(conta.getUsuario());
		Pedido pedido = null;
		Cliente cliente = null;
		List<Pedido> listPedidos = null;
		
		/*
		 * Strings para impressoras
		 */
		String impressoraBar = "";
		String impressoraCozinha = "";
		String impressoraPizzaria = "";
		
		if(conta.getTipo().matches("DELIVERY")){
			pedido = this.pedidoService.getPedidoByConta(idConta);
			cliente = this.clienteService.getClienteById(pedido.getCliente());
		}
		if(conta.getTipo().matches("SALAO")){
			listPedidos = this.pedidoService.listPedidosByConta(idConta);
		}
		Map<String, List<String>> listItens = this.itemService.listItemsByConta(idConta);
		
		// Setando variáveis para a impressão
		
		// Conta
		String tipo = conta.getTipo();
		while (tipo.length() < 8) {
			tipo = tipo + " ";
		}
		String numeroPedido = "";
		if (conta.getSequencia() > 0) {
			if (conta.getSequencia() <= 9) {
				numeroPedido = "00" + conta.getSequencia();
			} 
			if (conta.getSequencia() > 9 && conta.getSequencia() < 99) {
				numeroPedido = "0" + conta.getSequencia();
			}
		}
		if (conta.getMesa() > 0) {
			if (conta.getMesa() <= 9) {
				numeroPedido = "00" + conta.getMesa();
			} 
			if (conta.getMesa() > 9 && conta.getMesa() < 99) {
				numeroPedido = "0" + conta.getMesa();
			}
		}
		String observacao = null;
		if (conta.getTipo().matches("SALAO")) {
			for (Pedido pedidoOBS : listPedidos) {
				observacao = observacao + pedidoOBS.getObservacoes();
			}
		}
		if (conta.getTipo().matches("DELIVERY")) {	
			observacao = pedido.getObservacoes();
		}
		String pagamento = conta.getPagamento();
		String valor = conta.getValor().toString();
		String comissao = conta.getComissao().toString();
		String total = conta.getTotal().toString();
		String desconto = conta.getDesconto().toString();
		String recebido = conta.getRecebido().toString();
		String troco = conta.getTroco().toString();
		
		// Usuário
		String user = usuario.getNome();
		
		
		// Cliente
		String nome = "";
		String logradouro = "";
		String numero = "";
		String complemento = "";
		String bairro = "";
		String celular = "";
		String empresa = "";
		String telefone = "";
		String obs = "";
		if (cliente != null) {
			nome = cliente.getNome();
			logradouro = cliente.getRua();
			numero = cliente.getNumero();
			complemento = cliente.getComplemento();
			bairro = cliente.getBairro();
			celular = cliente.getCelular();
			empresa = cliente.getEmpresa();
			telefone = cliente.getTelefone();
			obs = cliente.getObservacoes();
		}
		
		// Tratando a Data
		String dia = "";
		if(calendar.get(Calendar.DAY_OF_MONTH) <= 9){
			dia = "0" + calendar.get(Calendar.DAY_OF_MONTH);
		} else {
			dia = "" + calendar.get(Calendar.DAY_OF_MONTH);
		}
		String mes = "";
		if(calendar.get(Calendar.MONTH) <= 9){
			mes = "0" + calendar.get(Calendar.MONTH);
		} else {
			mes = "" + calendar.get(Calendar.MONTH);
		}
		String ano = "";
		if(calendar.get(Calendar.YEAR) <= 9){
			ano = "0" + calendar.get(Calendar.YEAR);
		} else {
			ano = "" + calendar.get(Calendar.YEAR);
		}
		
		// Tratando a Hora
		String hora = "";
		if(calendar.get(Calendar.HOUR_OF_DAY) <= 9){
			hora = "0" + calendar.get(Calendar.HOUR_OF_DAY);
		} else {
			hora = "" + calendar.get(Calendar.HOUR_OF_DAY);
		}
		String minuto = "";
		if(calendar.get(Calendar.MINUTE) <= 9){
			minuto = ":0" + calendar.get(Calendar.MINUTE);
		} else {
			minuto = ":" + calendar.get(Calendar.MINUTE);
		}
		String segundo = "";
		if(calendar.get(Calendar.SECOND) <= 9){
			segundo = ":0" + calendar.get(Calendar.SECOND);
		} else {
			segundo = ":" + calendar.get(Calendar.SECOND);
		}
		
		String imprimir = ""
	                + "-RESTAURANTE-&-PIZZARIA--FEIJAO-DE-CORDA-ORIGINAL-\n"
	                + " \n"
                    + " PEDIDO: " + tipo
					+ " DATA: "+ dia + "/" + mes + "/" + ano + " HORA: " + hora + minuto + segundo + "\n";

		if(tipo.matches("DELIVERY")){
			imprimir = imprimir     
			+ "       - SEQ: " + numeroPedido + "\n";  
			if (tipo.matches("DELIVERY")) {
				imprimir = imprimir + ""
						+ "CLIENTE: =========================================\n"
						+ " " + nome + " - " + empresa + "\n"
						+ " " + logradouro + ", " + numero + "   " + complemento + " - " + bairro + "\n"
						+ " " + telefone + " ou " + celular + "\n"
				        + "==================================================\n";
			}
		} else {
			imprimir = imprimir  
			+ " MESA: " + numeroPedido + "\n";
		}
		imprimir = imprimir
					+ "******************* ITENS: ***********************\n";
		for (String key : listItens.keySet()) {
            List<String> value = listItens.get(key);
            String itemQtd = value.get(1);
            String itemTitulo = value.get(3);
            String itemObs = value.get(0);
            String itemValor = value.get(2);
            imprimir = imprimir + " -> QTD: " + itemQtd + " - " + itemTitulo +  "   - R$ " + itemValor + "\n" + itemObs + "\n";
            /*
             * Funções de tratamento de String para cada Impressora
             */
            if (value.get(5).matches("BAR")) {
            	if (value.get(6).matches("0")) {
            		impressoraBar = impressoraBar + "Sequencia: " + value.get(7) + "\n\n\n" + value.get(1) + " - "  + value.get(3) + " - " + value.get(0) + "\n\n\n\n\n\n\n";
            	}
            	if (value.get(7).matches("0")) {
            		impressoraBar = impressoraBar + "Mesa: " + value.get(6) + "\n\n\n" + value.get(1) + " - "  + value.get(3) + " - " + value.get(0) + "\n\n\n\n\n\n\n";
            	}
            }
            if (value.get(5).matches("COZINHA")) {
            	if (value.get(6).matches("0")) {
            		impressoraCozinha = impressoraCozinha + "Sequencia: " + value.get(7) + "\n\n\n" + value.get(1) + " - "  + value.get(3) + " - " + value.get(0) + "\n\n\n\n\n\n\n";
            	}
            	if (value.get(7).matches("0")) {
            		impressoraCozinha = impressoraCozinha + "Mesa: " + value.get(6) + "\n\n\n" + value.get(1) + " - "  + value.get(3) + " - " + value.get(0) + "\n\n\n\n\n\n\n";
            	}
            }
            if (value.get(5).matches("PIZZARIA")) {
            	if (value.get(6).matches("0")) {
            		impressoraPizzaria = impressoraPizzaria + "Sequencia: " + value.get(7) + "\n\n\n" + value.get(1) + " - "  + value.get(3) + " - " + value.get(0) + "\n\n\n\n\n\n\n";
            	}
            	if (value.get(7).matches("0")) {
            		impressoraPizzaria = impressoraPizzaria + "Mesa: " + value.get(6) + "\n\n\n" + value.get(1) + " - "  + value.get(3) + " - " + value.get(0) + "\n\n\n\n\n\n\n";
            	}            	
            }
            // FIM de tratamento de String para cada Impressora
		}
		if (tipo.matches("DELIVERY")) {
			imprimir = imprimir + ""
	                + "__________________________________________________\n"
					+ "TOTAL:                            R$ " + total + "\n" 
					+ "FORMA DE PAGAMENTO:               " + pagamento + "\n"
					+ "RECEBIDO:                         R$ " + recebido + "\n"
					+ "TROCO:                            R$ " + troco + "\n"
					+ "==================================================\n";
		
		} else {
		imprimir = imprimir + ""
	                + "__________________________________________________\n"
					+ "VALOR:                            R$ " + valor + "\n"
					+ "COMISSAO:                         R$  " + comissao + "\n\n"
					+ "TOTAL:                            R$ " + total + "\n" 
					+ "==================================================\n";
		}
		imprimir = imprimir + ""
					+ "\nUSUARIO: " + user   
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					;
		this.impressoraCaixa.imprimir(imprimir);
		if (!impressoraBar.isEmpty()) {
		//	this.impressoraBar.imprimir(impressoraBar);
		}
		if (!impressoraCozinha.isEmpty()) {
		//	this.impressoraCozinha.imprimir(impressoraCozinha);
		}
		if (!impressoraPizzaria.isEmpty()) {
		//	this.impressoraPizzaria.imprimir(impressoraPizzaria);
		}
		
	}

	@Override
	public void imprimirCaixa(String idCaixa) {
		Caixa caixa = caixaService.getCaixaById(Integer.parseInt(idCaixa));
		String	imprimir = "RESTAURANTE&PIZZARIA-FEIJAO-DE-CORDA-VILA-PRUDENTE" 
	                + "\n" + caixa.getDia() + "/" + caixa.getMes() + "/" + caixa.getAno()
	                + "\n" 
	                + "\n" + caixa.getValor()
	                + "\n\n\n"
					;
		this.impressoraCaixa.imprimir(imprimir);
	}
	@Override
	public void imprimirContasByCaixa(String idCaixa) {
		List<Conta> contas = contaService.listContasByCaixa(Integer.parseInt(idCaixa));
		String	imprimir = "";
		
		BigDecimal	dinheiro = new BigDecimal("0.00");
		BigDecimal	debito = new BigDecimal("0.00");
		BigDecimal	crediario = new BigDecimal("0.00");
		BigDecimal	credito = new BigDecimal("0.00");
		BigDecimal	ticket = new BigDecimal("0.00");
		BigDecimal	sodexo = new BigDecimal("0.00");
		BigDecimal	vr = new BigDecimal("0.00");
		BigDecimal	alelo = new BigDecimal("0.00");
		BigDecimal	vip = new BigDecimal("0.00");
		
		for (Conta conta : contas) {
			BigDecimal entrada = conta.getValor();
			if(conta.getPagamento().matches("DINHEIRO")){
				dinheiro = dinheiro.add(entrada);
			}
			if(conta.getPagamento().matches("DEBITO")){
				debito = debito.add(entrada);
			}
			if(conta.getPagamento().matches("CREDIARIO")){
				crediario = crediario.add(entrada);
			}
			if(conta.getPagamento().matches("CREDITO")){
				credito = credito.add(entrada);
			}
			if(conta.getPagamento().matches("TICKET")){
				ticket = ticket.add(entrada);
			}
			if(conta.getPagamento().matches("SODEXO")){
				sodexo = sodexo.add(entrada);
			}
			if(conta.getPagamento().matches("VR")){
				vr = vr.add(entrada);
			}
			if(conta.getPagamento().matches("ALELO")){
				vr = vr.add(entrada);
			}
			if(conta.getPagamento().matches("VIP")){
				vip = vip.add(entrada);
			}
		}
		imprimir = "\nDINHEIRO: " + dinheiro.toString() 
		 		   +"\nDEBITO: " + debito.toString() 
		 		   +"\nCREDIARIO: " + crediario.toString()
		 		   +"\nCREDITO: " + credito.toString()
		 		   +"\nTICKET: " + ticket.toString()
		 		   +"\nSODEXO: " + sodexo.toString()
		 		   +"\nVR: " + vr.toString()
		 		   +"\nALELO: " + alelo.toString()
		 		   +"\nVIP: " + vip.toString()
				   + "\n\n\n\n\n\n\n\n\n" 
				   ;
		this.impressoraCaixa.imprimir(imprimir);
	}
	
	@Override
	public void imprimirItensLastPedido() {

		// Tratando a Data
		String dia = "";
		if(calendar.get(Calendar.DAY_OF_MONTH) <= 9){
			dia = "0" + calendar.get(Calendar.DAY_OF_MONTH);
		} else {
			dia = "" + calendar.get(Calendar.DAY_OF_MONTH);
		}
		String mes = "";
		if(calendar.get(Calendar.MONTH) <= 9){
			mes = "0" + calendar.get(Calendar.MONTH+1);
		} else {
			mes = "" + calendar.get(Calendar.MONTH+1);
		}
		String ano = "";
		if(calendar.get(Calendar.YEAR) <= 9){
			ano = "0" + calendar.get(Calendar.YEAR);
		} else {
			ano = "" + calendar.get(Calendar.YEAR);
		}
		
		// Tratando a Hora
		String hora = "";
		if(calendar.get(Calendar.HOUR_OF_DAY) <= 9){
			hora = "0" + calendar.get(Calendar.HOUR_OF_DAY);
		} else {
			hora = "" + calendar.get(Calendar.HOUR_OF_DAY);
		}
		String minuto = "";
		if(calendar.get(Calendar.MINUTE) <= 9){
			minuto = ":0" + calendar.get(Calendar.MINUTE);
		} else {
			minuto = ":" + calendar.get(Calendar.MINUTE);
		}
		String segundo = "";
		if(calendar.get(Calendar.SECOND) <= 9){
			segundo = ":0" + calendar.get(Calendar.SECOND);
		} else {
			segundo = ":" + calendar.get(Calendar.SECOND);
		}
		
		ResultSet resultset = this.itemService.listItemsLastPedido();
		String impressoraBar = "";
		String impressoraCozinha = "";
		String impressoraPizzaria = "";

		try {
			while (resultset.next()) {
				    if (resultset.getString(12).matches("BAR")) {
				    	impressoraBar = impressoraBar + "==================BAR===";
				    	impressoraBar = impressoraBar + "  " + dia + "/" + mes + "/" + ano + " - " + hora + minuto + segundo;
		            	if (resultset.getString(10).matches("DELIVERY")) {
		            		impressoraBar = impressoraBar + "\n\n" + "SEQUENCIA:   " + resultset.getInt(4) + "\n\n\n" + resultset.getString(6) + " - "  + resultset.getInt(7) + "\n\n" + resultset.getString(8);
		            	}
		            	if (resultset.getString(10).matches("SALAO")) {
		            		impressoraBar = impressoraBar + "\n\n" + "MESA:   " + resultset.getInt(5) + "\n\n\n" + resultset.getString(6) + " - "  + resultset.getInt(7) + "\n\n" + resultset.getString(8);
		            	}
		            }
		            if (resultset.getString(12).matches("COZINHA")) {
		            	impressoraCozinha = impressoraCozinha + "==============COZINHA===";
		            	impressoraBar = impressoraBar + "  " + dia + "/" + mes + "/" + ano + " - " + hora + minuto + segundo;
		            	if (resultset.getString(10).matches("DELIVERY")) {
		            		impressoraCozinha = impressoraCozinha + "\n\n" + "SEQUENCIA:   " + resultset.getInt(4) + "\n\n\n" + resultset.getString(6) + " - "  + resultset.getInt(7) + "\n\n" + resultset.getString(8);
		            	}
		            	if (resultset.getString(10).matches("SALAO")) {
		            		impressoraCozinha = impressoraCozinha + "\n\n" + "MESA:   " + resultset.getInt(5) + "\n\n\n" + resultset.getString(6) + " - "  + resultset.getInt(7) + "\n\n" + resultset.getString(8);
		            	}
		            }
		            if (resultset.getString(12).matches("PIZZARIA")) {
		            	impressoraPizzaria = impressoraPizzaria + "=============PIZZARIA===";
		            	impressoraBar = impressoraBar + "  " + dia + "/" + mes + "/" + ano + " - " + hora + minuto + segundo;
		            	if (resultset.getString(10).matches("DELIVERY")) {
		            		impressoraPizzaria = impressoraPizzaria + "\n\n" + "SEQUENCIA:   " + resultset.getInt(4) + "\n\n\n" + resultset.getString(6) + " - "  + resultset.getInt(7) + "\n\n" + resultset.getString(8);
		            	}
		            	if (resultset.getString(10).matches("SALAO")) {
		            		impressoraPizzaria = impressoraPizzaria + "\n\n" + "MESA:   " + resultset.getInt(5) + "\n\n\n" + resultset.getString(6) + " - "  + resultset.getInt(7) + "\n\n" + resultset.getString(8);
		            	}            	
		            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
        // FIM de tratamento de String para cada Impressora
		if (!impressoraBar.isEmpty()) {
			this.impressoraBar.imprimir(impressoraBar);
		}
		if (!impressoraCozinha.isEmpty()) {
			this.impressoraCozinha.imprimir(impressoraCozinha);
		}
		if (!impressoraPizzaria.isEmpty()) {
			this.impressoraPizzaria.imprimir(impressoraPizzaria);
		}
	}
	
	@Override
	public void fechamentoDoCaixaDiario() {
		
		/*
		 *  Tratando a Data
		 *  	Acréscimo dos dígitos 
		 *  	ausentes no dias e meses com inteiros menores do que 9
		 */
		String dia = "";
		if (calendar.get(Calendar.DAY_OF_MONTH) <= 9) {
			dia = "0" + calendar.get(Calendar.DAY_OF_MONTH);
		} else {
			dia = "" + calendar.get(Calendar.DAY_OF_MONTH);
		}
		String mes = "";
		if (calendar.get(Calendar.MONTH) <= 9) {
			mes = "0" + calendar.get(Calendar.MONTH);
		} else {
			mes = "" + calendar.get(Calendar.MONTH);
		}
		String ano = "";
		if (calendar.get(Calendar.YEAR) <= 9) {
			ano = "0" + calendar.get(Calendar.YEAR);
		} else {
			ano = "" + calendar.get(Calendar.YEAR);
		}
		// FIM - Tratamento da Data
		/*
		 *  Tratando a Hora
		 *  	Confere os interiores menores que nove e acrescentam o zero antes 
		 */
		String hora = "";
		if (calendar.get(Calendar.HOUR_OF_DAY) <= 9) {
			hora = "0" + calendar.get(Calendar.HOUR_OF_DAY);
		} else {
			hora = "" + calendar.get(Calendar.HOUR_OF_DAY);
		}
		String minuto = "";
		if (calendar.get(Calendar.MINUTE) <= 9) {
			minuto = ":0" + calendar.get(Calendar.MINUTE);
		} else {
			minuto = ":" + calendar.get(Calendar.MINUTE);
		}
		String segundo = "";
		if (calendar.get(Calendar.SECOND) <= 9) {
			segundo = ":0" + calendar.get(Calendar.SECOND);
		} else {
			segundo = ":" + calendar.get(Calendar.SECOND);
		}
		// FIM - Tratamento da Data
		
		String imprimir = ""
				+ "           RESTAURANTE VILA PRUDENTE            "
                + "                    CAIXA                       "
                + " DATA: "+ dia + "/" + mes + "/" + ano + "            HORA: " + hora + ":" + minuto + ":" + segundo + "     "
                + " USUARIO: Cristiano Camejo                      "
                + "                                                "
                + "                    ITENS                       "
                + " IT | TITULO             | QTD | VALOR | TOTAL  "
                + "________________________________________________"
                + "                                                "
                ;
		   		ResultSet listItens = this.itemService.listItensLeftJoinCardapioByLastCaixa();   
		   		try {
		   			while (listItens.next()) {
		   				imprimir = imprimir + " " + listItens.getInt("id") + " | " + listItens.getString("titulo") + " | " + listItens.getInt("quantidade") + " | " + listItens.getBigDecimal("valor") + " | " + listItens.getBigDecimal("valor") + " \n";
		   			}
		   		} catch (SQLException e) {
					e.printStackTrace();
		   		}
               imprimir = imprimir + ""
                + "                                                "
                + " VENDAS:                                        "
                + "   A VISTA:                           R$ 115.00 "
                + "   DEBITO:                            R$ 115.00 "
                + "   CREDITO:                           R$ 115.00 "
                + "   TICKET:                            R$ 115.00 "
                + "   SODEX:                             R$ 115.00 "
                + "   VR:                                R$ 115.00 "
                + "   ALELLO:                            R$ 115.00 "
                + "                                  _____________ "
                + " TOTAL A VISTA:                       R$ 115.00 "
                + " TOTAL CREDITO:                       R$ 115.00 "
                ;
        this.impressoraCaixa.imprimir(imprimir);
	}

	
	/**
	 * FUNÇÃO PARA IMPRESSÃO
	 * @param texto
	 *
	 */
	public void imprime(String texto) {
		try {
		     // Abre o Stream da impressora
		     FileOutputStream fos = new FileOutputStream("COM3");
		     PrintStream ps = new PrintStream(fos);
		     // Imprime o texto
		     ps.print(texto);
		     // Quebra linha
		     ps.print("\f");
		     // Fecha o Stream da impressora
		     ps.close();
		     fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
