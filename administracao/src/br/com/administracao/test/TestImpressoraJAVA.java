package br.com.administracao.test;

import java.util.List;

import br.com.administracao.util.ImpressaoJAVA;

public class TestImpressoraJAVA {

	public static void main(String [] args) {
		impressoras();
		impressora();
		imprimir();
	}

	private static void impressoras() {
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		List<String> listarResultados = impressaoDao.retornaImpressoras();
		for(String i: listarResultados){
			System.out.println(i);
		}
	}
	
	private static void impressora(){
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		impressaoDao.detectaImpressoras("cozinha");
	}
	
	private static void imprimir(){
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		impressaoDao.imprime("RESTAURANTEVILAPRUDENTE||RESTAURANTEVILAPRUDENTE"
				+ "DELEVERY-22725374-LIGUE||DELEVERY-22725374-LIGUE"
				+ "CONFIRA-PROMOCOES-LIGUE||WHATS11-952.809.967-APP"
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "         DATA: 13/05/2016   HORA: 11:45         "
                + "                                                "
                + "         RESTAURANTE VILA PRUDENTE              "
                + "                                                "
				+ "         PEDIDO NUMERO                1         "
				+ "         ------------------------------         "
				+ "         CLIENTE:                               "
				+ "                 Antonio da Silva Sauro         "
				+ "                                                "
				+ "         ENDERECO:                              "
				+ "                   Rua Henry Ford, 3700         "
                + "                                                "
				+ "         BAIRRO:          Vila Prudente         "
				+ "         TELEFONE:	       127 777 333         "
				+ "                                                "
				+ "         *********** ITENS: ***********         "
				+ "                                                "
				+ "         1 Costela de Boi      R$ 13,90         "
				+ "         2 Lasanha             R$ 16,90         "
				+ "         1 Galinha Caipira     R$ 11,90         "
				+ "         1 Calabresa           R$ 11,90         "
                + "         ______________________________         "
				+ "                                                "
				+ "         VALOR TOTAL:          R$ 71,40         "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "RESTAURANTEVILAPRUDENTE||RESTAURANTEVILAPRUDENTE"
				+ "DELEVERY-22725374-LIGUE||WHATSAPP-(11)-952809967"
				+ "CONFIRA-PROMOCOES-LIGUE||TODOS-OS-BAIRROS-DE-SP-"
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				+ "                                                "
				);
	}
	
		
}
