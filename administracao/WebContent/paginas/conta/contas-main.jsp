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
       
       <div  class="quick-actions_homepage">
	    <ul class="quick-actions">
	      
			<c:forEach var="conta" items="${requestScope.listContas}">
				<c:if test="${conta.tipo == 'SALAO'}">
                	<li class="bg_lb"> <a href="contacontroller.do?acao=fecharConta&id=${conta.id}"> <i class="icon-edit"></i>${conta.mesa}</a> </li>
              	</c:if>
				<c:if test="${conta.tipo == 'DELIVERY'}">
                	<li class="bg_lg"> <a href="contacontroller.do?acao=fecharConta&id=${conta.id}"> <i class="icon-truck"></i>${conta.sequencia}</a> </li>
                </c:if>
			</c:forEach>

	    </ul>
	  </div>
     
     
      </div>
    </div>
  </div>
</div>

<c:import url="../../includes/footer.jsp"></c:import>