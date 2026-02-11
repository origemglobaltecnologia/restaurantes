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
            <h5>Cardapios</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Título</th>
                  <th>Descrição</th>
				  <th>Setor</th>
				  <th>Status</th>
				  <th>Valor</th>
				  <th>Data</th>
    			  <th>Update</th>
				  <th>Remove</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="cardapio" items="${requestScope.listCardapios}">
      		  <tr class="gradeX">
				<td>${cardapio.id}</td>
				<td>${cardapio.titulo}</td>
				<td>${cardapio.descricao}</td>
				<td>${cardapio.setor}</td>
				<td>${cardapio.status}</td>
				<td>${cardapio.valor}</td>
				<td>${cardapio.data}</td>
				<td class="td-actions">
					<a href="cardapiocontroller.do?acao=updateCardapio&id=${cardapio.id}"
						class="btn btn-small btn-success">
						<i class="btn-icon-only icon-ok"> </i>
					</a>
				</td>
				<td class="td-actions">
					<a href="cardapiocontroller.do?acao=removeCardapio&id=${cardapio.id}"
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