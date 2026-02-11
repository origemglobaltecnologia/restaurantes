<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:import url="../../includes/header.jsp"></c:import>
<c:import url="../../includes/menu.jsp"></c:import>
<c:import url="../../includes/sidebar.jsp"></c:import>



<!-- Adicionando Javascript -->
    <script type="text/javascript" >
    
    function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('rua').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('uf').value=("");
            document.getElementById('ibge').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('rua').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('uf').value=(conteudo.uf);
            document.getElementById('ibge').value=(conteudo.ibge);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(valor) {

        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('rua').value="...";
                document.getElementById('bairro').value="...";
                document.getElementById('cidade').value="...";
                document.getElementById('uf').value="...";
                document.getElementById('ibge').value="...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = '//viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };

    </script>



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
          <form action="clientecontroller.do" method="post" class="form-horizontal">
          <c:if test="${!empty cliente.id}">
			<div class="control-group">
				<label class="control-label">Id: </label>
				<div class="controls">
					<input type="hidden" name="id" value="${requestScope.cliente.id}" />
					<input type="text" size="8" class="span6" id="id" value="${requestScope.cliente.id}" disabled />
					<p class="help-block">Campo dezabilitado.</p>
				</div>
				<!-- /controls -->
			</div>
			<!-- /control-group -->
			</c:if>          
            <div class="control-group">
              <label class="control-label">Nome :</label>
              <div class="controls">
                <input name="nome" type="text" class="span11" placeholder="Nome" value="${requestScope.cliente.nome}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">CEP :</label>
              <div class="controls">
              	<input value="${requestScope.cliente.cep}" class="span3" name="cep" type="text" id="cep" value="" maxlength="9" onblur="pesquisacep(this.value);" />
              	<input class="span7" type="text" placeholder="Preenchimento automático de Endereço. Digite o cep e tecle TAB" disabled>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Rua - Número - Complemento :</label>
              <div class="controls">
              	<input name="rua" type="text" id="rua" value="${requestScope.cliente.rua}" class="span6" placeholder="Rua" />
              	<input name="numero" type="text" id="rua" value="${requestScope.cliente.numero}" class="span2" placeholder="Número" />
              	<input name="complemento" type="text" id="complemento" value="${requestScope.cliente.complemento}" class="span3	" placeholder="Complemento" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Bairro - Cidade - Estado :</label>
              <div class="controls">
              	<input name="bairro" type="text" id="bairro" value="${requestScope.cliente.bairro}" class="span5" placeholder="Bairro" />
              	<input name="cidade" type="text" id="cidade" value="${requestScope.cliente.cidade}" class="span4" placeholder="Cidade" />
              	<input name="uf" type="text" id="uf" value="${requestScope.cliente.uf}" class="span2" placeholder="UF" />
              </div>
            </div>
	        <input name="ibge" type="hidden" id="ibge" />
            <div class="control-group">
              <label class="control-label">Celular :</label>
              <div class="controls">
                <input name="celular" type="text" class="span11" placeholder="Celular" value="${requestScope.cliente.celular}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Email :</label>
              <div class="controls">
                <input name="email" type="text" class="span11" placeholder="Email" value="${requestScope.cliente.email}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Empresa :</label>
              <div class="controls">
                <input name="empresa" type="text" class="span11" placeholder="Empresa" value="${requestScope.cliente.empresa}" />
              </div>
            </div>            
            <div class="control-group">
              <label class="control-label">Telefone :</label>
              <div class="controls">
                <input name="telefone" type="text" class="span11" placeholder="Nome" value="${requestScope.cliente.telefone}" />
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Observações</label>
              <div class="controls">
                <textarea name="observacoes" class="span11" placeholder="observacoes">${requestScope.cliente.observacoes}</textarea>
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