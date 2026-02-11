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
          <form action="caixacontroller.do?acao=fechar" method="post" class="form-horizontal">
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
                <input name="usuario" type="hidden" class="span11" placeholder="Usuário" value="${requestScope.caixa.usuario}" />
                <input type="text" class="span11" value="${requestScope.caixa.usuario}" disabled />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Status</label>
              <div class="controls">
                <input name="status" type="hidden" value="${requestScope.caixa.status}" />
                <input type="text" class="span11" value="${requestScope.caixa.status}" disabled />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Valor :</label>
              <div class="controls">
                <input name="valor" type="hidden" value="${requestScope.caixa.valor}" />
                <input type="text" class="span11" value="${requestScope.caixa.valor}" disabled />
              </div>
            </div>
            <div class="form-actions">
              <button type="submit" class="btn btn-danger">Fechar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>

<c:import url="../../includes/footer.jsp"></c:import>