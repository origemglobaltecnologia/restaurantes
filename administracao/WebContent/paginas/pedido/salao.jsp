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
		<div id="breadcrumb">
			<a href="#" title="Go to Home" class="tip-bottom"><i
				class="icon-home"></i> Home</a> <a href="#">Addons pages</a> <a href="#"
				class="current">invoice</a>
		</div>
	</div>
	<div class="container-fluid" ng-controller="DemoCtrl">
		<div class="row-fluid">
			<div class="span12">
			  <div class="${classeBootstrap}">
			     <h3>${msg}</h3>
			  </div>
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"> <i class="icon-briefcase"></i>
						</span>
						<h5>Salão</h5>
					</div>
					<div class="widget-content">
						<div class="row-fluid">
							<div class="span12">
								<div class="controls controls-row">
									<label class="span2 m-wrap">Item</label>
									<ui-select ng-model="cardapio.selected" theme="select2"
										ng-required="true" class="span7 m-wrap"> <match
										placeholder="Selecione um cardapio na lista...">{{$select.selected.titulo}}</match>
									<choices
										repeat="cardapio in cardapios | propsFilter: {titulo: $select.search, id: $select.search}">
									<div ng-bind-html="cardapio.titulo | highlight: $select.search"></div>
									<small> Valor: {{cardapio.valor}} <br /> Código: <span
										ng-bind-html="''+cardapio.id | highlight: $select.search"></span>
									</small> </choices> </ui-select>
									<button class="btn btn-primary span3 m-wrap"
										ng-click="adicionarItem(cardapio.selected)"
										ng-disabled="!cardapio.selected">Adicionar Item</button>
								</div>
								<div>{{previewpizza}}</div>
								<hr>

							</div>
							<c:url var="addAction" value=""></c:url>
							<form action="pedidocontroller.do" method="post"
								name="formPedido" id="edit-profile" class="form-horizontal">
								<div class="row-fluid">
									<div class="span12">
									<div class="control-group">
										<input name="mesa" type="text" class="span4" placeholder="Mesa" ng-required="true" /> 
										<input name="usuario" type="text" class="span4" placeholder="Garçom" ng-required="true" />
										<input name="pessoas" type="text" class="span4" placeholder="Pessoas" ng-required="true"  />
									</div>
								<hr>
								
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
												<tr ng-class="classe"
													ng-repeat="itemSelecionado in itensSelecionados">
													<td><input name="quantidades" ng-change="fazerSoma(quantia, itemSelecionado.id)" ng-model="quantia" class="form-control" id="quantidades" value="{{quantidade}}" /></td>
													<td><input type="hidden" name="itens" id="itens"
														value="{{itemSelecionado.id}}" ng-required="true" /> <input type="hidden"
														name="titulos" id="titulos"
														value="{{itemSelecionado.titulo}}" />
														{{itemSelecionado.titulo}}</td>
													<td><input name="observacao" id="observacao" /></td>
													<td><input name="valoresItens" id="valorItens"
														value="{{itemSelecionado.valor}}" /></td>
													<td>
														<button type="button"
															ng-click="apagarItem(itemSelecionado)"
															class="btn btn-danger">X</button>
													</td>
												</tr>
											</tbody>
										</table>
										<input type="hidden" name="cliente" value="1" /> 
										<input type="hidden" name="recebido" value="0" /> 
										<input type="hidden" name="troco" value="0" /> 
										<input type="hidden" name="sequencia" value="0" /> 
										<input type="hidden" name="tipo" value="SALAO" /> 
										<input type="hidden" name="valor" id="valor" value='{{total}}' />

										<div class="control-group">
											<label class="control-label">Observações :</label>
											<div class="controls">
												<textarea name="observacoes" class="span11">${requestScope.pedido.observacoes}</textarea>
											</div>
										</div>
										<div class="pull-right">
											<br> <input class="btn btn-primary btn-large pull-right"
												type="submit" value="Finalizar Pedido">
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
</div>
	<c:import url="../../includes/footer.jsp"></c:import>