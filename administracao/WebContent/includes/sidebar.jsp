<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--sidebar-menu-->

<div id="sidebar"> <a href="#" class="visible-phone"><i class="icon icon-th"></i>Menu</a>
  <ul>
    <li><a href="maincontroller.do"><i class="icon icon-home"></i> <span>Dashboard</span></a> </li>
    <li class="submenu"> <a href="#"><i class="icon icon-shopping-cart"></i> <span>Pedidos</span></a>
      <ul>
        <li><a href="pedidocontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
    	<li><a href="pedidocontroller.do?acao=addPedidoSalao"><i class="icon icon-edit"></i> <span>Salão</span></a> </li>
    	<li><a href="pedidocontroller.do?acao=addPedidoDelivery"><i class="icon icon-truck"></i> <span>Delivery</span></a> </li>
    	<li><a href="pedidocontroller.do?acao=listPedidos"><i class="icon icon-list"></i> <span>Pedidos</span></a> </li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-sitemap"></i> <span>Contas</span></a>
      <ul>
        <li><a href="contacontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
        <li><a href="contacontroller.do?acao=fechar"><i class="icon icon-key"></i> <span>Fechar</span> </a> </li>
    	<li><a href="contacontroller.do?acao=receber"><i class="icon icon-money"></i> <span>Receber</span> </a> </li>
    	<li><a href="contacontroller.do?acao=listContas"><i class="icon icon-list"></i> <span>Contas</span></a> </li>
      </ul>
    </li>
     <li class="submenu"> <a href="#"><i class="icon icon-list-ol"></i> <span>Itens</span> </a>
      <ul>
        <li><a href="itemcontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
    	<li><a href="itemcontroller.do?acao=addItem"><i class="icon icon-pencil"></i> <span>Item</span></a> </li>
    	<li><a href="itemcontroller.do?acao=listItens"><i class="icon icon-list"></i> <span>Itens Geral</span></a> </li>
    	<li><a href="itemcontroller.do?acao=pizzaria"><i class="icon icon-list"></i> <span>Itens Pizzaria</span></a> </li>
    	<li><a href="itemcontroller.do?acao=cozinha"><i class="icon icon-list"></i> <span>Itens Cozinha</span></a> </li>
    	<li><a href="itemcontroller.do?acao=bar"><i class="icon icon-list"></i> <span>Itens Bar</span></a> </li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-money"></i> <span>Caixas</span></a>
      <ul>
        <li><a href="caixacontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
        <li><a href="caixacontroller.do?acao=addCaixa"><i class="icon-key"></i> <span>Abrir</span></a> </li>
    	<li><a href="caixacontroller.do?acao=caixa"><i class="icon-money"></i> <span>Fechar</span></a> </li>
     	<li><a href="caixacontroller.do?acao=listCaixas"><i class="icon icon-list"></i> <span>Caixas</span></a> </li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-picture"></i> <span>Cardápios</span> </a>
      <ul>
        <li><a href="cardapiocontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
    	<li><a href="cardapiocontroller.do?acao=addCardapio"><i class="icon icon-pencil"></i> <span>Cardápio</span></a> </li>
    	<li><a href="cardapiocontroller.do?acao=listCardapios"><i class="icon icon-list"></i> <span>Cardápios</span></a> </li>
      </ul>
    </li>
    <li class="submenu"> <a href="#"><i class="icon icon-user"></i> <span>Clientes</span> </a>
      <ul>
        <li><a href="clientecontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
    	<li><a href="clientecontroller.do?acao=addCliente"><i class="icon icon-pencil"></i> <span>Cliente</span></a> </li>
    	<li><a href="clientecontroller.do?acao=listClientes"><i class="icon icon-list"></i> <span>Clientes</span></a> </li>
      </ul>
    </li>
    <li class="submenu active"> <a href="#"><i class="icon icon-user-md"></i> <span>Usuários</span> </a>
      <ul>
        <li><a href="usuariocontroller.do"><i class="icon icon-home"></i> <span>Home</span></a> </li>
    	<li><a href="usuariocontroller.do?acao=addUsuario"><i class="icon icon-pencil"></i> <span>Usuário</span></a> </li>
    	<li><a href="usuariocontroller.do?acao=listUsuarios"><i class="icon icon-list"></i> <span>Usuários</span></a> </li>
      </ul>
    </li>
    <li><a href=""><i class="icon icon-home"></i> <span>Sair</span></a> </li>
  </ul>
</div>