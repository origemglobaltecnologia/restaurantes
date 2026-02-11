package br.com.administracao.test;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class TestImpressaoPrintStream {

	public static void main(String[] args) {
		String imprimir = "RESTAURANTEVILAPRUDENTE||RESTAURANTEVILAPRUDENTE"
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
				+ "                                                ";
		try {
		     // Abre o Stream da impressora
		     FileOutputStream fos = new FileOutputStream("COM3");
		     PrintStream ps = new PrintStream(fos);
		     // Imprime o texto
		     ps.print(imprimir);
		     // Quebra linha
		     ps.print("\f");
		     // Fecha o Stream da impressora
		     ps.close();
		     fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
