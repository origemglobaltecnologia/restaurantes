package br.com.administracao.impressora;

import java.util.List;

import br.com.administracao.util.ImpressaoJAVA;

public class ImpressoraCaixaImpl implements ImpressoraCaixa {
	
/*    byte[] comecoNegrito = {0x1B, 0x45};  
    byte[] fimNegrito = {0x1B, 0x46};  
    byte[] comecoExp = {0x1B, 0x0E};  
    byte[] fimExp = {0x1B, 0x14};  
    byte[] comecoCond = {0x1B, 0x0F};  
    byte[] fimCond = {0x1B, 0x12};  
    byte[] comecoNorm = {0x1B, 0x4D};  
    byte[] comecoItal = {0x1B, 0x34};  
    byte[] fimItal = {0x1B, 0x35};  
    byte[] comecoElite = {0x1B, 0x50};  
    byte[] subinhado = {0x1B, 0x2D};  
              
    String txt  
        = new String(comecoNegrito)  
        + new String(" negrito\n")  
        + new String(fimNegrito)  
        + new String(comecoCond)  
        + new String(" condensado\n")  
        + new String(fimCond)  
        + new String(comecoElite)  
        + new String(" elite\n")  
        + new String(comecoNorm)  
        + new String(" normal\n")  
        + new String(comecoItal)  
        + new String(" italico\n")  
        + new String(fimItal)  
        + new String(comecoExp)  
        + new String(" expandido\n\n\n\n\n\n")  
        + new String(fimExp);  */
	
	@Override
	public void impressoras() {
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		List<String> listarResultados = impressaoDao.retornaImpressoras();
		for(String i: listarResultados){
			System.out.println(i);
		}
	}
	
	@Override
	public void impressora(){
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		impressaoDao.detectaImpressoras("caixa");
	}
	
	@Override
	public void imprimir(String texto){
		ImpressaoJAVA impressaoDao = new ImpressaoJAVA();
		impressaoDao.imprime("CAIXA \n" + texto);
	}
}
