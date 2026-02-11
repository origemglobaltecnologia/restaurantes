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
          <h5>Item</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="itemcontroller.do" method="post" class="form-horizontal">
          <c:if test="${!empty item.id}">
			<div class="control-group">
				<label class="control-label">Id: </label>
				<div class="controls">
					<input type="hidden" name="id" value="${requestScope.item.id}" />
					<input type="text" size="8" class="span6" id="id" value="${requestScope.item.id}" disabled />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>          
            <div class="control-group">
              <label class="control-label">Conta :</label>
              <div class="controls">
                <input name="conta" type="text" class="span11" placeholder="Conta" value="${requestScope.item.conta}" />
              </div>
            </div>
			<div class="control-group">
              <label class="control-label">Pedido :</label>
              <div class="controls">
                <input name="pedido" type="text" class="span11" placeholder="Pedido" value="${requestScope.item.pedido}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Cardápio :</label>
              <div class="controls">
                <input name="cardapio" type="text" class="span11" placeholder="Cardápio" value="${requestScope.item.cardapio}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Quantidade :</label>
              <div class="controls">
                <input name="quantidade" type="text" class="span11" placeholder="Quantidade" value="${requestScope.item.quantidade}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Valor :</label>
              <div class="controls">
                <input name="valor" type="text" class="span11" placeholder="Valor" value="${requestScope.item.valor}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">STATUS</label>
              <div class="controls">
                <select name="status">
                  <option value="${requestScope.item.status}">${requestScope.item.status}</option>
                  <option value="ATIVO">ATIVO</option>
                  <option value="INATIVO">INATIVO</option>
                  <option value="FALTA">FALTA</option>
                </select>
              </div>
            </div>
		    <div class="control-group">
              <label class="control-label">Observação</label>
              <div class="controls">
                <textarea name="observacao" class="span11" placeholder="Observação">${requestScope.item.observacao}</textarea>
              </div>
            </div>            
            <div class="form-actions">
              <button type="submit" class="btn btn-success">Salvar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>

<c:import url="../../includes/footer.jsp"></c:import>