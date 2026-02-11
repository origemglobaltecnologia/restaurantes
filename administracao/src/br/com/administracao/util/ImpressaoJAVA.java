package br.com.administracao.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;

public class ImpressaoJAVA {
	
	private PrintService impressora; // O Atributo citado anteriormente
	
	public List<String> retornaImpressoras(){
        try {
            List<String> listaImpressoras = new ArrayList<String>();
            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);  
            for (PrintService p : ps) {  
                listaImpressoras.add(p.getName());     
            }  
            return listaImpressoras;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
    }
	
	public void detectaImpressoras(String impressoraSelecionada) {  //Passa por parâmetro o nome salvo da impressora
	        try {  
	            DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
	            PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);  
	            for (PrintService p : ps) {  
	                if(p.getName()!=null && p.getName().contains(impressoraSelecionada)){  
	                    impressora = p;  
	                }     
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
	
	public  boolean imprime(String texto) { 
		this.retornaImpressoras();
		DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;  
        PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);  
        for (PrintService p : ps) {  
            if(p.getName()!=null && p.getName().contains("MP-2500 TH")){  
                impressora = p;  
            }     
        }  
        if (impressora == null) {  
            JOptionPane.showMessageDialog(null, "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa."); 
        } else {  
            try {  
                DocPrintJob dpj = impressora.createPrintJob();  
                InputStream stream = new ByteArrayInputStream((texto + "\n").getBytes());  
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;  
                Doc doc = new SimpleDoc(stream, flavor, null);  
                dpj.print(doc, null);  
                return true;  
            } catch (PrintException e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }

}
