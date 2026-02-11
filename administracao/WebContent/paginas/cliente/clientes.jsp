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
            <h5>Clientes</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>Id</th>
                  <th>nome</th>
                  <th>endereco</th>
				  <th>celular</th>
				  <th>empresa</th>
				  <th>telefone</th>
				  <th>observacoes</th>
    			  <th>Update</th>
				  <th>Remove</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="cliente" items="${requestScope.listClientes}">
      		  <tr class="gradeX">
				<td>${cliente.id}</td>
				<td>${cliente.nome}</td>
				<td>${cliente.rua}, ${cliente.numero}, ${cliente.bairro}</td>
				<td>${cliente.celular}</td>
				<td>${cliente.empresa}</td>
				<td>${cliente.telefone}</td>
				<td>${cliente.observacoes}</td>
				<td class="td-actions">
					<a href="clientecontroller.do?acao=updateCliente&id=${cliente.id}"
						class="btn btn-small btn-success">
						<i class="btn-icon-only icon-ok"> </i>
					</a>
				</td>
				<td class="td-actions">
					<a href="clientecontroller.do?acao=removeCliente&id=${cliente.id}"
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