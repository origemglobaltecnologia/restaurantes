package br.com.administracao.test;

import br.com.administracao.util.ConectaMySQL;

public class TestConectaMySQL {
	public static void main(String [] args){
			
		if (ConectaMySQL.getConnection() != null) {
            System.out.println("STATUS--->Conectado com sucesso!");
        } else {
        	System.out.println("STATUS--->Não foi possivel realizar conexão");
        }
	}
}
