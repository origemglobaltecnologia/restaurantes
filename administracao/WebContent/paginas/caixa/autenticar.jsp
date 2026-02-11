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
          <h5>Autenticação Necessária</h5>
        </div>
        <div class="widget-content nopadding">
          <form action="caixacontroller.do?acao=autenticar" method="post" class="form-horizontal">
            <div class="control-group">
              <label class="control-label">Usuário :</label>
              <div class="controls">
                <input name="email" type="text" class="span11" placeholder="Email" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Senha :</label>
              <div class="controls">
                <input name="senha" type="password" class="span11" placeholder="XXXXXXX" />
              </div>
            </div>
            	<input name="usuario" type="hidden" value="1" />
                <input name="valor" type="hidden" value="0.00" />
                <input name="status" type="hidden" value="ABERTO" />
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