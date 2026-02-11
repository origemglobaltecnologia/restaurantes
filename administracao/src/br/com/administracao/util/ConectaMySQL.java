package br.com.administracao.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConectaMySQL
{
	public static Connection getConnection()
	{
		Connection conexao = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/restaurante";
			String usuario = "root";
			String senha = "";
			
			/*String url = "jdbc:mysql://127.2.232.130:3306/feijao";
			String usuario = "adminTekYggW";
			String senha = "VjUeDeCzWzBc";*/
			
			/*String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9147019";
			String usuario = "sql9147019";
			String senha = "d21fjWM8zZ";*/
			
			/*String url = "jdbc:mysql://185.27.134:3306/b31_19269758_restaurante";
			String usuario = "b31_19269758";
			String senha = "Camejo952809967";*/
			
			// db4free.net
			/*String url = "jdbc:mysql://db4free.net:3306/feijaodecorda";
			String usuario = "feijaodecorda";
			String senha = "Camejo952809967";*/
			
			conexao = (Connection) DriverManager.getConnection(url, usuario, senha);
		}
		catch(SQLException e)
		{	
			
			System.out.println("Ocorreu um erro de SQL. Erro: " + e.getMessage());
		
		} catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
		}

		return conexao;
	}
}

