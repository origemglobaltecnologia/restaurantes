package br.com.administracao.test;

import java.util.List;

import br.com.administracao.util.ImpressaoJAVA;

public class TestImpressora {
	
	public static final char[] START = new char[] { 0x1B, '@' };
	public static final char[] CORTAR = new char[] { 0x1B, 'm' };
	private static final byte[] CHAR_SIZE_0 = { 0x1B, 0x21, 0x00 };
	private static final byte[] CHAR_SIZE_1 = { 0x1B, 0x21, 0x10 };
	private static final byte[] CHAR_SIZE_2 = { 0x1B, 0x21, 0x20 };
	@SuppressWarnings("unused")
	private static final byte[] CHAR_SIZE_3 = { 0x1B, 0x21, 0x30 };	
	private static final byte[] AVANCA = { 0x19 };
	private static final byte[] POSESQUEDA = { 0x1B, 'j', '0' };
	private static final byte[] POSCENTRO = { 0x1B, 'j', '1' };
	private static final byte[] POSDIREITA = { 0x1B, 'j', '2' };
	@SuppressWarnings("unused")
	private static final byte[] TABHORIZONTAL = { 0x09 };
	@SuppressWarnings("unused")
	private static final byte[] startTextCondensado = {0x1B, 0x0F };
	@SuppressWarnings("unused")
	private static final byte[] endTextCondensado = { 0x12 };
	@SuppressWarnings("unused")
	private static final byte[] startTextExpandido = { 0x1B, 'W', '1' };
	@SuppressWarnings("unused")
	private static final byte[] endtTextExpandido = { 0x1B, 'W', '0' };
	@SuppressWarnings("unused")
	private static final char[] QRCODE_PREFIX = new char[] { 0x1B, 0x81 };
	@SuppressWarnings("unused")
	private static final char[] ESPACOHORIZONTAL = new char[] { 0x20 };	
	@SuppressWarnings("unused")
	private static final byte[] SET_LINE_SPACING_24 = { 0x1B, 0x33, (byte) 24 };
	@SuppressWarnings("unused")
	private static final byte[] SET_LINE_SPACING_30 = { 0x1B, 0x33, (byte) 30 };
	@SuppressWarnings("unused")
	private static final byte[] PRINT_AND_FEED_PAPER = { 0x1B, 0x4A, 0x00 };	
	@SuppressWarnings("unused")
	private static final byte[] REVERSAO = { 0x10, 0x4D, 0x01 };

	public static void main(String [] args) {
	//	impressoras();
	//	impressora();
		imprimirSalao();
	}
	
	public void impressoras() {
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		List<String> listarResultados = impressaoDao.retornaImpressoras();
		for(String i: listarResultados){
			System.out.println(i);
		}
	}
	public void impressora(){
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		impressaoDao.detectaImpressoras("bar");
	}
	public static void imprimirSalao(){
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		String imprimir = "RESTAURANTE & PIZZARIA\n"
                     + "     FEIJAO DE CORDA     \n"
                     + "         ORIGINAL        \n"
                     + "                         \n"
                     + " PEDIDO:        DELIVERY \n"
			     	 + " SEQUENCIA:          001 \n"
			     	 + "                         \n"
			         + " DATA:        21/02/1978 \n"               
			         + " HORA:          16:40:45 \n" 
					 + "=========================\n"
					 + " OBSERVACOES:            \n"
					 + "=========================\n"
					 + "******** ITENS: *********\n"
					 + "  1 -                    \n"
					 + "    - Título Cardapio I  \n"
					 + "    Observação do mesmo  \n"
					 + "                R$ 29.90 \n"
					 + "  1 -                    \n"
					 + "    - Título Cardapio I  \n"
					 + "    Observação do mesmo  \n"
					 + "                R$ 29.90 \n"
					 + "  1 -                    \n"
					 + "    - Título Cardapio I  \n"
					 + "    Observação do mesmo  \n"
					 + "                R$ 29.90 \n"
			         + "_________________________\n"
					 + "VALOR:       R$   159.70 \n"
		             + "                         \n"
		             + "                         \n"
		             + "                         \n"
		             + "                         \n"
		             + "                         \n"
		             + "                         \n"
		             + "                         \n"
		             + "                         \n"
			;
		impressaoDao.imprime(titulo() + imprimir);
		
	}
	
	
	
	public static String titulo () {
		String Ptxt =
		new String (START)+
		new String(CHAR_SIZE_2)+
		new String (POSCENTRO)+
		new String(AVANCA)+
		new String(CORTAR);
		return Ptxt;
	}
	public static String item () {
		String Ptxt =
		new String (START)+
		new String(CHAR_SIZE_1)+
		new String (POSESQUEDA)+
		new String(AVANCA)+
		new String(CORTAR);
		return Ptxt;
	}
	public static String outros () {
		String Ptxt =
		new String (START)+
		new String(CHAR_SIZE_0)+
		new String (POSDIREITA)+
		new String(AVANCA)+
		new String(CORTAR);
		return Ptxt;
	}
}
