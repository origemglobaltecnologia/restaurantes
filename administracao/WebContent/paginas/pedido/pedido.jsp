<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

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
          <h5>Pedido</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="pedidocontroller.do" method="post" class="form-horizontal">
			<c:if test="${!empty pedido.id}">
			<div class="control-group">
				<label class="control-label">Id: </label>
				<div class="controls">
					<input type="hidden" name="id" value="${requestScope.pedido.id}" />
					<input type="text" size="8" class="span6" id="id" value="${requestScope.pedido.id}" disabled />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>          
			<div class="control-group">
              <label class="control-label">Tipo</label>
              <div class="controls">
                <select name="tipo">
                  <option>${requestScope.pedido.tipo}</option>
                  <option value="SALAO">SALAO</option>
                  <option value="DELIVERY">DELIVERY</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Mesa :</label>
              <div class="controls">
                <input name="mesa" type="text" class="span11" placeholder="Mesa" value="${requestScope.pedido.mesa}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Sequencia :</label>
              <div class="controls">
                <input name="sequencia" type="text" class="span11" placeholder="Sequencia" value="${requestScope.pedido.sequencia}" />
              </div>
            </div>            
			<div class="control-group">
              <label class="control-label">Cliente :</label>
              <div class="controls">
                <input name="cliente" type="text" class="span11" placeholder="Cliente" value="${requestScope.pedido.cliente}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Usuário :</label>
              <div class="controls">
                <input name="usuario" type="text" class="span11" placeholder="Usuário" value="${requestScope.pedido.usuario}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Pessoas :</label>
              <div class="controls">
                <input name="pessoas" type="text" class="span11" placeholder="Pessoas" value="${requestScope.pedido.pessoas}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Valor :</label>
              <div class="controls">
                <input name="valor" type="text" class="span11" placeholder="Valor" value="${requestScope.pedido.valor}" />
              </div>
            </div>
			<div class="control-group">
              <label class="control-label">Conta :</label>
              <div class="controls">
                <input name="conta" type="text" class="span11" placeholder="Conta" value="${requestScope.pedido.conta}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Observações :</label>
              <div class="controls">
                <textarea name="observacoes" class="span11" >${requestScope.pedido.observacoes}</textarea>
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
            <h5>Itens</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>Pedido</th>
				  <th>Sequencia</th>
				  <th>Mesa</th>
				  <th>Titulo</th>
				  <th>Quantidade</th>
				  <th>Observação</th>
				  <th>Valor</th>
				  <th>Status</th>
				  <th>Visualizar</th>
				  <th>Cancelar</th>
                </tr>
              </thead>
              <tbody>
             	<c:forEach var="outEntry" items="${listItens}">
				 	<tr class="gradeX">
    			        <td><c:out value="${outEntry.key}"/></td>
    			        <% int contador = 0; %>
    			        <c:forEach var="inEntry" items="${outEntry.value}">
    			        <% 
    			        if (contador >= 3) {
    			        %>	
    			        	<td><c:out value="${inEntry}"/></td>
    			        <%
    			        }
    			        contador++;
    			        %>
			         	</c:forEach>
			         	<td class="td-actions">
							<a href="itemcontroller.do?acao=updateItem&id=${outEntry.key}"
								class="btn btn-small btn-success">
								<i class="btn-icon-only icon-eye-open"> </i>
							</a>
						</td>
						<td class="td-actions">
							<a href="itemcontroller.do?acao=cancelItem&id=${outEntry.key}"
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