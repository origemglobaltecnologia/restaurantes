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
          <h5>Usuário</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="usuariocontroller.do" method="post" class="form-horizontal">
            <c:if test="${!empty usuario.id}">
			<div class="control-group">
				<label class="control-label">Id: </label>
				<div class="controls">
					<input type="hidden" name="id" value="${requestScope.usuario.id}" />
					<input type="text" size="8" class="span6" id="id" value="${requestScope.usuario.id}" disabled />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>
            <div class="control-group">
              <label class="control-label">Nome :</label>
              <div class="controls">
                <input name="nome" type="text" class="span11" placeholder="Nome" value="${requestScope.usuario.nome}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Endereço :</label>
              <div class="controls">
                <input name="endereco" type="text" class="span11" placeholder="Endereço" value="${requestScope.usuario.endereco}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">E-mail :</label>
              <div class="controls">
                <input name="email" type="text" class="span11" placeholder="E-mail : seuemail@seuprovedor.com" value="${requestScope.usuario.email}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Password input</label>
              <div class="controls">
                <input name="senha" type="password"  class="span11" placeholder="Senha" value="${requestScope.usuario.senha}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Telefone :</label>
              <div class="controls">
                <input name="telefone" type="text" class="span11" placeholder="Telefone" value="${requestScope.usuario.telefone}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Observações</label>
              <div class="controls">
                <textarea name="observacoes" class="span11" >${requestScope.usuario.observacoes}</textarea>
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