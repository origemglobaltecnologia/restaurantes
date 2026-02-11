<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>

<div id="content" ng-app="demo">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Addons pages</a> <a href="#" class="current">invoice</a> </div>
  </div>
  <div class="container-fluid" ng-controller="DemoCtrl">
    <div class="row-fluid">
      <div class="span12">
      	<div class="${classeBootstrap}">
		   <h3>${msg}</h3>
		</div>
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-briefcase"></i> </span>
            <h5 >Delivery</h5>
          </div>
          <div class="widget-content">
            <div class="row-fluid">
              <div class="span6">
			     <div class="controls controls-row">
				  <label class="span2 m-wrap">Item</label>
				  <ui-select ng-model="cardapio.selected" theme="select2" ng-required="true" class="span7 m-wrap"> 
				  	<match placeholder="Selecione um cardapio na lista...">{{$select.selected.titulo}}</match>
						<choices repeat="cardapio in cardapios | propsFilter: {titulo: $select.search, id: $select.search}">
							<div ng-bind-html="cardapio.titulo | highlight: $select.search"></div>
								<small> 
									Valor: {{cardapio.valor}} <br /> 
									Código: <span ng-bind-html="''+cardapio.id | highlight: $select.search"></span>
								</small> 
						</choices> 
				  </ui-select>				  
				  <button class="btn btn-primary span3 m-wrap" ng-click="adicionarItem(cardapio.selected)"	ng-disabled="!cardapio.selected">Adicionar Item</button>
			     </div>
			     <div>{{previewpizza}}</div>
			     <hr>
			     <div class="controls controls-row">
				  <label class="span2 m-wrap">Cliente</label>
			     	<ui-select ng-model="cliente.selected" theme="select2" ng-disabled="disabled" class="span7 m-wrap">
					   <match placeholder="Selecione um cliente na lista...">{{$select.selected.nome}}</match>
					    <choices repeat="cliente in clientes | propsFilter: {nome: $select.search, id: $select.search, telefone: $select.search, rua: $select.search, numero: $select.search}">
					      <div ng-bind-html="cliente.nome | highlight: $select.search"></div>
						      <small>
							     nome: {{cliente.nome}} <br />
								 rua: {{cliente.rua}} <br />
								 numero: {{cliente.numero}} <br />
								 complemento: {{cliente.complemento}} <br />
								 bairro: {{cliente.bairro}} <br />
								 telefone: <span ng-bind-html="''+cliente.telefone | highlight: $select.search"></span>
						      </small>
					    </choices>
 					</ui-select>
			     </div>
              </div>
              <div class="span6">
                <table class="table table-bordered table-invoice">
                  <thead>
                    <tr>
                      <th colspan="2">Cliente</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="width30">Nome:</td>
                      <td class="width70"><strong>{{cliente.selected.nome}}</strong></td>
                    </tr>
                    <tr>
                      <td>Telefone:</td>
                      <td><strong>{{cliente.selected.telefone}}</strong></td>
                    </tr>
                    <tr>
	                  <td class="width30">Endereço:</td>
	                    <td class="width70">
	                    	<strong>{{cliente.selected.rua}}, {{cliente.selected.numero}} {{cliente.selected.complemento}} - {{cliente.selected.bairro}}</strong>
	        	        </td>
                  	</tr>
                    </tbody>
                </table>
              </div>
            </div>
			<c:url var="addAction" value=""></c:url>
			<form action="pedidocontroller.do" method="post" name="formPedido" id="edit-profile" class="form-horizontal">
            <div class="row-fluid">
              <div class="span12">
                <table class="table table-bordered table-invoice-full">
                  <thead>
                    <tr>
                      <th>Quantidade</th>
                      <th>Título</th>
                      <th>Observações</th>
                      <th>Valor</th>
                      <th>Excluir</th>
                    </tr>
                  <tbody>
                   <tr ng-class="classe" ng-repeat="itemSelecionado in itensSelecionados">
					  <td>
						<input name="quantidades" ng-change="fazerSoma(quantia, itemSelecionado.id)" ng-model="quantia" class="form-control" id="quantidades" value="{{quantidade}}" />
					  </td>
					  <td>
						<input type="hidden" name="itens" id="itens" value="{{itemSelecionado.id}}" />
						<input type="hidden" name="titulos" id="titulos" value="{{itemSelecionado.titulo}}" />
						{{itemSelecionado.titulo}}
					  </td>
					  <td>
						<input name="observacao" id="observacao" />
					  </td>
					  <td>
						<input name="valoresItens" id="valorItens" value="{{itemSelecionado.valor}}" />
					  </td>
					  <td>
						<button type="button" ng-click="apagarItem(itemSelecionado)" class="btn btn-danger">X</button>
					  </td>
				   </tr>
                  </tbody>
                </table>
                   	<input type="hidden" name="cliente" value="{{cliente.selected.id}}" ng-required="true" />
					<input type="hidden" name="sequencia" value="0" />
			  		<input type="hidden" name="usuario" value="1" />
			  		<input type="hidden" name="mesa" value="0" />
			  		<input type="hidden" name="pessoas" value="1" />
			  		<input type="hidden" name="tipo" value="DELIVERY" />
                <table class="table table-bordered table-invoice-full">
                  <tbody>
                    <tr>
                      <td class="msg-invoice" width="85%">
                      <select name="pagamento" class="span4 form-control" ng-required="true">
						<option value="-">Pagamento</option>
						<option value="DINHEIRO">DINHEIRO</option>
						<option value="DEBITO">DEBITO</option>
						<option value="CREDIARIO">CREDIARIO</option>
						<option value="CREDITO">CREDITO</option>
						<option value="SODEXO">SODEXO</option>
						<option value="TICKET">TICKET</option>
						<option value="ALELO">ALELO</option>
					  </select>
					  <input type="hidden" name="valor" id="valor" value='{{total}}' />
					  <input type="hidden" name="troco" id="diferenca" value='{{diferenca}}' />
					  
					  <input name="recebido" type="text" ng-change="fazerTroco(troco)" ng-model="troco" class="span3 form-control" id="troco" value='{{troco}}' style="float:right" />
					  <span style="float:right; margin:7px;"> Troco - </span>
					  <textarea style="float:right" name="observacoes"></textarea>
					  <span style="float:right; margin:7px;"> Observações - </span>
                      <td class="right">
                      	<strong><span>Total</span></strong> <br>
                        <strong>Recebido</strong> <br>
                        <strong>Troco</strong></td>
                      <td class="right">
                      	<strong>
                      	{{total | currency:"R$ "}}<br>
                        {{troco | currency:"R$ "}}<br>
                        {{diferenca | currency:"R$ "}}
                        </strong>
                       </td>
                    </tr>
                  </tbody>
                </table>
				<div class="pull-right">
                  <h4><span>Total:</span> {{total | currency:"R$ "}}</h4>
                  <br>
                   <input class="btn btn-primary btn-large pull-right" type="submit" value="Finalizar Pedido"> 
                </div>
              </div>
            </div>
			</form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<c:import url="../../includes/footer.jsp"></c:import>