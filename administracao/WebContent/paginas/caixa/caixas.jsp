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
            <h5>Caixas</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Usuário</th>
                  <th>Status</th>
                  <th>Valor</th>
                  <th>Data</th>
                  <th>Hora</th>
                  <th>Visualizar</th>
                  <th>Cancelar</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="caixa" items="${requestScope.listCaixas}">
      		  <tr class="gradeX">
				<td>${caixa.id}</td>
				<td>${caixa.usuario}</td>
				<td>${caixa.status}</td>
				<td>${caixa.valor}</td>
				<td>${caixa.dia}/${caixa.mes}/${caixa.ano}</td>
				<td>${caixa.hora}:${caixa.minuto}:${caixa.segundo}</td>
				<td class="td-actions">
					<a href="caixacontroller.do?acao=updateCaixa&id=${caixa.id}"
						class="btn btn-small btn-success">
						<i class="btn-icon-only icon-eye-open"> </i>
					</a>
				</td>
				<td class="td-actions">
					<a href="impressaocontroller.do?acao=imprimirCaixa&id=${caixa.id}"
						class="btn btn-info btn-small">
						<i	class="btn-icon-only icon-print"> </i>
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