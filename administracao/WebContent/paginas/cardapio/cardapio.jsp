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
          <h5>Cardápio</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="cardapiocontroller.do" method="post" class="form-horizontal">
          <c:if test="${!empty cardapio.id}">
			<div class="control-group">
				<label class="control-label">Id: </label>
				<div class="controls">
					<input type="hidden" name="id" value="${requestScope.cardapio.id}" />
					<input type="text" size="8" class="span6" id="id" value="${requestScope.cardapio.id}" disabled />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>          
            <div class="control-group">
              <label class="control-label">Título :</label>
              <div class="controls">
                <input name="titulo" type="text" class="span11" placeholder="Título" value="${requestScope.cardapio.titulo}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Descrição</label>
              <div class="controls">
                <textarea name="descricao" class="span11" placeholder="descricao">${requestScope.cardapio.descricao}</textarea>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Setor</label>
              <div class="controls">
                <select name="setor">
                  <option value="${requestScope.cardapio.setor}">${requestScope.cardapio.setor}</option>
                  <option value="COZINHA">COZINHA</option>
                  <option value="PIZZARIA">PIZZARIA</option>
                  <option value="BAR">BAR</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">STATUS</label>
              <div class="controls">
                <select name="status">
                  <option value="${requestScope.cardapio.status}">${requestScope.cardapio.status}</option>
                  <option value="ATIVO">ATIVO</option>
                  <option value="INATIVO">INATIVO</option>
                  <option value="FALTA">FALTA</option>
                </select>
              </div>
            </div>
			<div class="control-group">
              <label class="control-label">Valor :</label>
              <div class="controls">
                <input name="valor" type="text" class="span11" placeholder="Valor R$ X.XX" value="${requestScope.cardapio.valor}" />
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