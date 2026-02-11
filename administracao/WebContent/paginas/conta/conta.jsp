<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>

<div id="content" ng-app="demo">
<div id="content-header">
  <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Form elements</a> <a href="#" class="current">Common elements</a> </div>
</div>
<div class="container-fluid" ng-controller="DemoCtrl">
  <div class="row-fluid">
    <div class="span12">
      <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5>Conta</h5>
        </div>
        <div class="widget-content nopadding">

          <form action="contacontroller.do" method="post" class="form-horizontal">
			<c:if test="${!empty conta.id}">
				<input type="hidden" name="id" value="${requestScope.conta.id}" />
			</c:if>
			
			<div class="control-group">
              <label class="control-label">Usuário</label>
              <div class="controls">
              <c:if test="${requestScope.conta.status != 'PAGA'}">
                <input name="usuario" type="text"  class="span11" placeholder="Usuário" value="${requestScope.conta.usuario}" />
              </c:if>
              <c:if test="${requestScope.conta.status == 'PAGA'}">
                <input name="usuario" type="text"  class="span11" placeholder="Usuário" value="${requestScope.conta.usuario}" disabled />
              </c:if>
              </div>
            </div>
            <input name="caixa" type="hidden" class="span11" placeholder="Caixa" value="${requestScope.conta.caixa}" />
			
			<div class="row-fluid">
				<div class="span11">
					<div class="span6">
						<div class="control-group">
			              <label class="control-label">Tipo</label>
			              <div class="controls">
			              	<input name="tipo" type="hidden" value="${requestScope.conta.tipo}" />
			              	<input name="tipo" type="text" class="span11" placeholder="Tipo" value="${requestScope.conta.tipo}" disabled />
			              </div>
			            </div>
					</div>
					<div class="span6">
						<c:if test="${conta.tipo == 'SALAO'}">
							<div class="control-group">
				              <label class="control-label">Mesa :</label>
				              <div class="controls">
				              	<input name="mesa" type="hidden" value="${requestScope.conta.mesa}" />
				                <input name="mesa" type="text" class="span11" placeholder="Mesa" value="${requestScope.conta.mesa}" disabled />
				              </div>
				            </div>
						</c:if>
						<c:if test="${conta.tipo == 'DELIVERY'}">
				            <div class="control-group">
				              <label class="control-label">Sequencia :</label>
				              <div class="controls">
				                <input name="sequencia" type="hidden" value="${requestScope.conta.sequencia}" />
				                <input name="sequencia" type="text" class="span11" placeholder="Sequencia" value="${requestScope.conta.sequencia}" disabled />
				              </div>
				            </div>
						</c:if>
					</div>
				</div>
			</div>
            
			<div class="row-fluid">
				<div class="span11">
					<div class="span4">
						<div class="control-group">
				           <label class="control-label">Valor :</label>
				           <div class="controls">
				           	 <input name="valor" type="hidden" value="${requestScope.conta.valor}" />
				             <input name="valor" type="text" class="span12" size="12" placeholder="R$ 0,00" value="${requestScope.conta.valor}" disabled />
				           </div>
				        </div>
					</div>
					<div class="span4">
						<div class="control-group">
				           <label class="control-label">Comissão :</label>
				           <div class="controls">
				              <input name="comissao" type="hidden" value="${requestScope.conta.comissao}" />
				              <input name="comissao" type="text" class="span12" placeholder="R$ 0,00" value="${requestScope.conta.comissao}" disabled />
				           </div>
				        </div>
					</div>
					<div class="span4">
						<div class="control-group">
			              <label class="control-label">Desconto :</label>
			              <div class="controls">
			              <c:if test="${requestScope.conta.status != 'PAGA'}">
			              	<input name="desconto" type="hidden" id="desconto" value="{{desconto}}" />
			              	<c:if test="${requestScope.conta.desconto == '0.00'}">
								<input ng-init="desconto='${requestScope.conta.desconto}'" ng-change="gerarDesconto(desconto)" ng-model="desconto" type="text" class="span12" id="desconto" value="{{desconto}}" />
							 </c:if>
			               	 <c:if test="${requestScope.conta.desconto != '0.00'}">
								<input ng-init="desconto='${requestScope.conta.desconto}'" type="text" class="span12" value="{{desconto}}" disabled />
							 </c:if>
						  </c:if>
						  <c:if test="${requestScope.conta.status == 'PAGA'}">
								<input value="${requestScope.conta.desconto}" type="text" class="span12" disabled />
						  </c:if>
			              </div>
			            </div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span11">
					<div class="span4">
						 <div class="control-group">
			              <label class="control-label">Total :</label>
			              <div class="controls">
			              	<div >
			                	<input name="total" ng-model="total" type="hidden" id="total" value="{{total}}" />
			               		<input ng-init="total='${requestScope.conta.total}'" type="text" class="span12" value="{{total}}" disabled />
			                </div>
			              </div>
			            </div>		
					</div>
					<div class="span4">
						<div class="control-group">
			              <label class="control-label">Recebido :</label>
			              <div class="controls">
			              <c:if test="${requestScope.conta.status != 'PAGA'}">
			              	<input name="recebido" type="hidden" id="troco" value="{{troco}}" />
			              	<c:if test="${requestScope.conta.pagamento == 'DINHEIRO'}">
			              		<input name="recebido" ng-init="troco='${requestScope.conta.recebido}'" type="text" ng-change="fazerTroco(troco)" ng-model="troco" class="span12" id="troco" value='{{troco}}' />
			              	</c:if>		
			              </c:if>
			              <c:if test="${requestScope.conta.status == 'PAGA'}">
			              	<input name="recebido" type="hidden" id="troco" value="{{troco}}" />
			              	<c:if test="${requestScope.conta.pagamento == 'DINHEIRO'}">
			              		<input name="recebido" ng-init="troco='${requestScope.conta.recebido}'" type="text" ng-change="fazerTroco(troco)" ng-model="troco" class="span12" id="troco" value='{{troco}}' disabled />
			              	</c:if>		
			              </c:if>	                
			              </div>
			            </div>	
					</div>
					<div class="span4">
						<div class="control-group">
			              <label class="control-label">Troco :</label>
			              <div class="controls">
			              <c:if test="${requestScope.conta.status != 'PAGA'}">
			              	<input name="troco" ng-model="diferenca" type="hidden" id="diferenca" value="{{diferenca}}" />
			              	<c:if test="${requestScope.conta.pagamento == 'DINHEIRO'}">
			             	  	<input ng-init="diferenca='${requestScope.conta.troco}'" ng-model="diferenca" type="text" class="span12" value="{{diferenca}}" />
			            	</c:if>	
			              </c:if>
			              <c:if test="${requestScope.conta.status == 'PAGA'}">
			              	<input name="troco" ng-model="diferenca" type="hidden" id="diferenca" value="{{diferenca}}" />
			              	<c:if test="${requestScope.conta.pagamento == 'DINHEIRO'}">
			             	  	<input ng-init="diferenca='${requestScope.conta.troco}'" ng-model="diferenca" type="text" class="span12" value="{{diferenca}}" disabled />
			            	</c:if>	
			              </c:if>
			              </div>
			            </div>
					</div>
				</div>
			</div>
            <div class="control-group">
              <label class="control-label">Pagamento</label>
              <div class="controls">
              <c:if test="${requestScope.conta.status != 'PAGA'}">
                <select name="pagamento" ng-init="pagamento='${requestScope.conta.pagamento}'" ng-model="pagamento">
                  <option value="${requestScope.conta.pagamento}">${requestScope.conta.pagamento}</option>
                  <option value="DINHEIRO">DINHEIRO</option>
                  <option value="DEBITO">DEBITO</option>
                  <option value="CREDIARIO">CREDIARIO</option>
                  <option value="CREDITO">CREDITO</option>
                  <option value="TICKET">TICKET</option>
                  <option value="SODEXO">SODEXO</option>
                  <option value="VR">VR</option>
				  <option value="ALELO">ALELO</option>
                  <option value="VIP">VIP</option>
                </select>
              </c:if>
              <c:if test="${requestScope.conta.status == 'PAGA'}">
                <select name="pagamento" disabled>
                  <option value="${requestScope.conta.pagamento}">${requestScope.conta.pagamento}</option>
                </select>
              </c:if>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Status</label>
              <div class="controls">
	            <c:if test="${requestScope.conta.status == ''}">
	            	<select name="status">
	                  <option value="${requestScope.conta.status}">${requestScope.conta.status}</option>
	                  <option value="ABERTA">ABERTA</option>
	                </select>
				</c:if>
	            <c:if test="${requestScope.conta.status == 'ABERTA'}">
	            	<select name="status">
	                  <option value="${requestScope.conta.status}">${requestScope.conta.status}</option>
	                  <option value="FECHADA">FECHADA</option>
	                </select>
				</c:if>
	            <c:if test="${requestScope.conta.status == 'FECHADA'}">
	            	<select name="status">
	                  <option value="${requestScope.conta.status}">${requestScope.conta.status}</option>
	                  <option value="PAGA">PAGA</option>
					  <option value="ABERTA">ABERTA</option>
	                </select>
				</c:if> 
				<c:if test="${requestScope.conta.status == 'PAGA'}">
	            	<select name="status" disabled>
	                  <option value="${requestScope.conta.status}">${requestScope.conta.status}</option>
	                </select>
				</c:if> 
              </div>
            </div>		
            <div class="control-group">
              <label class="control-label">Observações</label>
              <div class="controls">
              <c:if test="${requestScope.conta.status != 'PAGA'}">
                <textarea name="observacao" class="span11" >${requestScope.conta.observacao}</textarea>
              </c:if>
              <c:if test="${requestScope.conta.status == 'PAGA'}">
                <textarea name="observacao" class="span11" disabled >${requestScope.conta.observacao}</textarea>
              </c:if>
              </div>
            </div>
            <div class="form-actions">
            <c:if test="${requestScope.conta.status != 'PAGA'}">
				<button type="submit" class="btn btn-success">Salvar</button>
			</c:if>
            </div>
          </form>
        </div>
      </div>
       <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <h5>Pedidos</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                	<th>Id</th>
                	<th>Tipo</th>
                	<th>Mesa</th>
					<th>Sequencia</th>
					<th>Cliente</th>
					<th>Usuário</th>
					<th>Pessoas</th>
					<th>Valor</th>
					<th>Hora</th>
					<th>Conta</th>
					<th>Observações</th>
					<th>Visualizar</th>
					<th>Cancelar</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="pedido" items="${requestScope.listPedidos}">
	      		  <tr class="gradeX">
					<td>${pedido.id}</td>
					<td>${pedido.tipo}</td>
					<td>${pedido.mesa}</td>
					<td>${pedido.sequencia}</td>
					<td>${pedido.cliente}</td>
					<td>${pedido.usuario}</td>
					<td>${pedido.pessoas}</td>
					<td>${pedido.valor}</td>
					<td><c:if test="${pedido.hora <= 9}">0</c:if>${pedido.hora}:<c:if test="${pedido.minuto <= 9}">0</c:if>${pedido.minuto}:<c:if test="${pedido.segundo <= 9}">0</c:if>${pedido.segundo}</td>
					<td>${pedido.conta}</td>
					<td>${pedido.observacoes}</td>
					<td class="td-actions">
						<a href="pedidocontroller.do?acao=updatePedido&id=${pedido.id}"
							class="btn btn-small btn-success">
							<i class="btn-icon-only icon-eye-open"> </i>
						</a>
					</td>
					<td class="td-actions">
						<a href="pedidocontroller.do?acao=cancelPedido&id=${pedido.id}"
							class="btn btn-danger btn-small">
							<i	class="btn-icon-only icon-remove"> </i>
						</a>
					</td>
					</tr>
				 </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
    </div>
  </div>
</div>
</div>

<c:import url="../../includes/footer.jsp"></c:import>