package br.com.administracao.model;

import java.math.BigDecimal;

public class Item {

	private Integer id;
	private Integer conta;
	private Integer caixa;
	private Integer pedido;
	private String tipo;
	private Integer cardapio;
	private Integer quantidade;
	private String observacao;
	private String status;
	private Integer mesa;
	private Integer sequencia;
	private Integer hora;
	private Integer minuto;
	private Integer segundo;
	private BigDecimal valor;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	public Integer getSegundo() {
		return segundo;
	}

	public void setSegundo(Integer segundo) {
		this.segundo = segundo;
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}
	
	public Integer getCaixa() {
		return caixa;
	}

	public void setCaixa(Integer caixa) {
		this.caixa = caixa;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido_id(Integer pedido) {
		this.pedido = pedido;
	}

	public Integer getCardapio() {
		return cardapio;
	}

	public void setCardapio(Integer cardapio) {
		this.cardapio = cardapio;
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

}
