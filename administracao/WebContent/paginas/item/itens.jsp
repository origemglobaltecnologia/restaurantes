<%@page import="java.sql.ResultSet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>

<div id="content">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
       

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