<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#">Addons pages</a> <a href="#" class="current">invoice</a> </div>
    <h1>${msg}</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-briefcase"></i> </span>
            <h5 >Conta Detalhada</h5>
            
          </div>
          <div class="widget-content">
            <div class="row-fluid">
              <div class="span6">

                <table class="">
                  <tbody>
                    <tr>
                      <td><h4>Conta: </h4></td>
                    </tr>
                    <tr>
                      <td>Tipo: </td><td>${requestScope.conta.tipo}</td>
                    </tr>
                    <c:if test="${requestScope.caixa.sequencia > 0}">
                    <tr>
                      <td>Sequencia: </td><td>${requestScope.caixa.sequencia}</td>
                    </tr>
                    </c:if>
                    <c:if test="${requestScope.conta.mesa > 0}">
                    <tr>
                      <td>Mesa: </td><td>${requestScope.conta.mesa}</td>
                    </tr>
                    </c:if>
                    <tr>
                      <td>Hora: </td><td><c:if test="${requestScope.conta.hora <= 9}">0</c:if>${requestScope.conta.hora}:<c:if test="${requestScope.conta.minuto <= 9}">0</c:if>${requestScope.conta.minuto}:<c:if test="${requestScope.conta.segundo <= 9}">0</c:if>${requestScope.conta.segundo}</td>
                    </tr>
                    <tr>
                      <td >Usuário: </td><td>${requestScope.usuario.nome}</td>
                    </tr>
                    <tr>
                      <td >Observação: </td><td> ${requestScope.conta.observacao}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="span6">
                <table class="table table-bordered table-invoice">
                  <tbody>
                    <tr>
                    <tr>
                      <td class="width30">Cliente: </td>
                      <td class="width70"><strong>${requestScope.cliente.nome}</strong></td>
                    </tr>
                    <tr>
                      <td>Empresa:</td>
                      <td><strong>${requestScope.cliente.empresa}</strong></td>
                    </tr>
                    <tr>
                      <td>Telefone:</td>
                      <td><strong>${requestScope.cliente.telefone}</strong></td>
                    </tr>
                    <tr>
                      <td>Celular:</td>
                      <td><strong>${requestScope.cliente.celular}</strong></td>
                    </tr>
					<tr>
                  <td class="width30"> Endereço:</td>
                    <td class="width70"> CEP: <strong>${requestScope.cliente.cep}</strong> <br>
                      ${requestScope.cliente.rua} <br>
                      ${requestScope.cliente.numero} <br>
                      ${requestScope.cliente.complemento} <br>
                      ${requestScope.cliente.bairro} </td>
                  </tr>
                    </tbody>
                  
                </table>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span12">
                <table class="table table-bordered table-invoice-full">
                  <thead>
                    <tr>
                      <th class="head0">Título</th>
                      <th class="head1">Observação</th>
                      <th class="head0 right">Qta</th>
                      <th class="head1 right">Preço</th>
                      <th class="head0 right">Subtotal</th>
                    </tr>
                  </thead>
                  <tbody>
                	<c:forEach var="item" items="${listItens}">
					<tr>
		              <td><c:out value="${item.value[3]}"/></td>
		              <td><c:out value="${item.value[0]}"/></td>
		              <td class="right"><c:out value="${item.value[1]}"/></td>
		              <td class="right"><c:out value="${item.value[4]}"/></td>
		              <td class="right"><strong><c:out value="${item.value[2]}"/></strong></td>
		             </tr>
		             </c:forEach>
                  </tbody>
                </table>
                <table class="table table-bordered table-invoice-full">
                  <tbody>
                    <tr>
                      <td class="msg-invoice" width="85%"><h4>Pagamento: </h4>
                        <a href="#" class="tip-bottom" title="Wire Transfer">Metodo: ${requestScope.conta.pagamento}</a> |  <a href="#" class="tip-bottom" title="Bank account">Valor: ${requestScope.conta.valor}</a> |  <a href="#" class="tip-bottom" title="SWIFT code">Comissão: ${requestScope.conta.comissao} </a>|  <a href="#" class="tip-bottom" title="IBAN Billing address">Desconto: ${requestScope.conta.desconto} </a></td>
                      <td class="right"><strong>Total: </strong> <br>
                        <strong>Recebido:  </strong> <br>
                        <strong>Troco:  </strong></td>
                      <td class="right"><strong>${requestScope.conta.total}<br>
                        ${requestScope.conta.recebido} <br>
                        ${requestScope.conta.troco}</strong></td>
                    </tr>
                  </tbody>
                </table>
                <div class="pull-right">
                  <h4><span>Total :</span> ${requestScope.conta.total}</h4>
                  <br>
                    <c:if test="${requestScope.conta.status == 'ABERTA'}">
	            		<a href="contacontroller.do?acao=updateConta&id=${requestScope.conta.id}" class="btn btn-danger btn-large pull-right" href="">Fechar</a>
					</c:if>
		            <c:if test="${requestScope.conta.status == 'FECHADA'}">
		            	<a href="contacontroller.do?acao=updateConta&id=${requestScope.conta.id}" class="btn btn-success btn-large pull-right" href="">Receber</a>
					</c:if> 
                  <a href="impressaocontroller.do?acao=imprimirConta&id=${requestScope.conta.id}" class="btn btn-primary btn-large pull-right" href="">Imprimir</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<c:import url="../../includes/footer.jsp"></c:import>