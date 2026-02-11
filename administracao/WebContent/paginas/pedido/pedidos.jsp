<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>

<div id="content">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
       

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
							<i class="btn-icon-only icon-ok"> </i>
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