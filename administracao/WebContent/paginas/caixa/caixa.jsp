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
  <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Form elements</a> <a href="#" class="current">Common elements</a> </div>
</div>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12">
      <div class="widget-box">
        <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
          <h5>Caixa</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="caixacontroller.do?acao=caixa" method="post" class="form-horizontal">
            <c:if test="${!empty caixa.id}">
			<div class="control-group">
				<label class="control-label">Id: </label>
				<div class="controls">
					<input type="hidden" name="id" value="${requestScope.caixa.id}" />
					<input type="text" size="8" class="span6" id="id" value="${requestScope.caixa.id}" disabled />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>
            <div class="control-group">
              <label class="control-label">Usuário :</label>
              <div class="controls">
                <input name="usuario" type="text" class="span11" placeholder="Usuário" value="${requestScope.caixa.usuario}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Status</label>
              <div class="controls">
                <select name="status">
                  <option value="${requestScope.caixa.status}">${requestScope.caixa.status}</option>
                  <option value="ABERTO">ABERTO</option>
                  <option value="FECHADO">FECHADO</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Valor :</label>
              <div class="controls">
                <input name="valor" type="text" class="span11" placeholder="R$ 0,00" value="${requestScope.caixa.valor}" />
              </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn btn-success">Salvar</button>
            </div>
          </form>
        </div>
      </div>
      
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <h5>Contas</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                	<th>Tipo</th>
                	<th>Mesa</th>
                	<th>Sequencia</th>
                	<th>Usuário</th>
					<th>Total</th>	
					<th>Pagamento</th>
					<th>Hora</th>
					<th>Status</th>
					<th>Observação</th>
					<th>Visualizar</th>
					<th>Cancelar</th>
                </tr>
              </thead>
              <tbody>
			  <c:forEach var="conta" items="${requestScope.listContas}">
      		  <tr class="gradeX">
				<td>${conta.tipo}</td>
				<td>${conta.mesa}</td>
				<td>${conta.sequencia}</td>
				<td>${conta.usuario}</td>
				<td>${conta.total}</td>
				<td>${conta.pagamento}</td>
				<td><c:if test="${conta.hora <= 9}">0</c:if>${conta.hora}:<c:if test="${conta.minuto <= 9}">0</c:if>${conta.minuto}:<c:if test="${conta.segundo <= 9}">0</c:if>${conta.segundo}</td>
				<td>${conta.status}</td>
				<td>${conta.observacao}</td>
				<td class="td-actions">
					<a href="contacontroller.do?acao=updateConta&id=${conta.id}"
						class="btn btn-small btn-success">
						<i class="btn-icon-only icon-eye-open"> </i>
					</a>
				</td>
				<td class="td-actions">
					<a href="contacontroller.do?acao=cancelConta&id=${conta.id}"
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