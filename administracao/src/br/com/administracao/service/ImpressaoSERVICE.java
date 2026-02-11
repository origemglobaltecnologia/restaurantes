package br.com.administracao.service;

import br.com.administracao.model.Item;
import br.com.administracao.model.Pedido;

public interface ImpressaoSERVICE {

	public void imprimirCaixa(String caixa);
	public void imprimirConta(String conta);
	public void imprimirPedido(Pedido pedido);
	public void imprimirItem(Item item);
	
	public void imprimirItensLastPedido();
	public void fechamentoDoCaixaDiario();
	public void imprimirItensByCaixa(String caixa);
	public void imprimirContasByCaixa(String idCaixa);
	
}
