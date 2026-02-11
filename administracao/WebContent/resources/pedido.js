'use strict';

var app = angular.module('demo', ['ngSanitize', 'ui.select']);

app.filter('propsFilter', function() {
  return function(items, props) {
    var out = [];

    if (angular.isArray(items)) {
      items.forEach(function(item) {
        var itemMatches = false;

        var keys = Object.keys(props);
        for (var i = 0; i < keys.length; i++) {
          var prop = keys[i];
          var text = props[prop].toLowerCase();
          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
            itemMatches = true;
            break;
          }
        }

        if (itemMatches) {
          out.push(item);
        }
      });
    } else {
      out = items;
    }

    return out;
  }
});

app.controller('DemoCtrl', function($scope, $http) {
 
  $scope.cardapio = {};
  var carregarCardapios = function(){
	  $http.get("cardapiocontroller.do?acao=listarjson").success(function(data){
		  $scope.cardapios = data;
	  });
  }	
  carregarCardapios();
  
  $scope.cliente = {};
  var carregarClientes = function(){
	  $http.get("clientecontroller.do?acao=listarjson").success(function(data){
		  $scope.clientes = data;
	  });
  }	
  carregarClientes();

  /**
   * Função para inserção dos itens no pedido
   * 
   * 	Recebeo item e checa se é pizza sabores, caso sim, 
   * 	armazena o resuldade até que o contador seja zerado,
   * 	após insere na lista de itens selecionados
   */
  // Variáveis nessessárias para a função
  var contador = 0;
  var id = 0;
  var titulo = "";
  $scope.previewpizza = "";
  var valor = 0.00;
  
  $scope.adicionarItem = function(itemSelecionadoJS) {
  	  // Testa o contador, se maior que zero, armazena o item em lista 
	  if(contador > 0){
		  // Seta os sabores da pizza para insercao do item ao pedido
		  contador--;
		  titulo = titulo + " | " + itemSelecionadoJS.titulo;
		  $scope.previewpizza = $scope.previewpizza + " | " + itemSelecionadoJS.titulo;
		  if(itemSelecionadoJS.valor > valor){
			  valor = itemSelecionadoJS.valor;
		  }
		  if(contador == 0){
			  // Array de pizzas
			  $scope.itensSelecionados.push(
				{
				  "id":id,
				  "titulo":titulo,
				  "valor":valor
				}
			  );
			//  id = 0;
			//  titulo = "";
			//  valor = 0.00;
			  $scope.somar(valor);
			  delete $scope.previewpizza;
			  delete $scope.itemSelecionadoJS;
		  }
		  
	  } else {
		  // Testa se o id NÃO é de pizza sabores, 
		  if(itemSelecionadoJS.id != 1 && itemSelecionadoJS.id != 2){
			  // caso não sejam os tais, armazena normalmente o item
			  $scope.itensSelecionados.push(angular.copy(itemSelecionadoJS));
		  	  $scope.somar(itemSelecionadoJS.valor);
		  	  delete $scope.itemSelecionadoJS;
	  	  } else {
	  		  // caso contrario inicia a pizza
	  		  id = (itemSelecionadoJS.id);
		  	  delete $scope.itemSelecionadoJS;
	  	  }
	  }
	  
	  if(itemSelecionadoJS.id == 1){
		  titulo = itemSelecionadoJS.titulo;
		  $scope.previewpizza = itemSelecionadoJS.titulo;
		  contador = 2;
	  }
	  if(itemSelecionadoJS.id == 2){
		  titulo = itemSelecionadoJS.titulo;
		  $scope.previewpizza = itemSelecionadoJS.titulo;
		  contador = 3;
	  }
	  
  };
  // Fim da função de insert item  
  
  $scope.apagarItem = function(itemSelecionado) {
	  var pos = $scope.itensSelecionados.indexOf(itemSelecionado);
	  $scope.itensSelecionados.splice(pos, 1);
	  $scope.subtrair(itemSelecionado.valor);
	  delete $scope.itemSelecionado;
  };
  
  $scope.itensSelecionados = [];
  $scope.total = 0;
  $scope.somar = function(valorSomar) {
	  $scope.total += parseFloat(valorSomar);
  }
  
  $scope.subtrair = function(valorSubtrair) {
	  $scope.total -= parseFloat(valorSubtrair);
  }
  
  $scope.pagamento = null;
  $scope.setPagamento = function(pagamento) {
      $scope.pagamento = pagamento;
    };
  
    $scope.quantia = 1;
    $scope.fazerSoma = function(quantia, cardapio) {
    	for (var j in $scope.itensSelecionados) {
    		if ($scope.itensSelecionados[j].id == cardapio) {
    			var valorAgregar = $scope.itensSelecionados[j].valor * (quantia - 1)
    			$scope.somar(valorAgregar);
    			$scope.itensSelecionados[j].valor = $scope.itensSelecionados[j].valor * quantia;
    		}
    	}
    };
    
  $scope.troco = 0;
  $scope.diferenca = 0;
  $scope.fazerTroco = function(troco) {
      $scope.diferenca = ($scope.total - troco)*-1;
    };
   
  $scope.setTotal = function(total) {
      $scope.total = total;
    };
  
    $scope.desconto = 0;
    $scope.gerarDesconto = function(desconto) {
        $scope.total = ($scope.total - desconto);
      };

});