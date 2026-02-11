package br.com.administracao.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.administracao.dao.CardapioDAO;
import br.com.administracao.dao.CardapioDAOImpl;
import br.com.administracao.model.Cardapio;

public class TestCardapioDAO {

	static CardapioDAOImpl cardapioDAOImpl = new CardapioDAOImpl();

	public static void main(String[] args) {
		testAddCardapio();
		testUpdateCardapio();
		testListCardapios();
		testGetCardapioById(4);
		testRemoveCardapio(4);
	}

	public static void testAddCardapio(){
		Cardapio cardapio = new Cardapio();
		cardapio.setTitulo("titulo");
		cardapio.setDescricao("descricao");
		cardapio.setSetor("setor");
		cardapio.setStatus("status");
		cardapio.setValor(new BigDecimal(0.00));;
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		cardapio.setData(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR));
		// DAO INSERT
		CardapioDAO cardapioDao = cardapioDAOImpl;
		cardapioDao.addCardapio(cardapio);
	}
	
	public static void testUpdateCardapio(){
		Cardapio cardapio = new Cardapio();
		cardapio.setId(5);
		cardapio.setTitulo("titulo");
		cardapio.setDescricao("descricao");
		cardapio.setSetor("setor");
		cardapio.setStatus("status");
		cardapio.setValor(new BigDecimal(0.00));;
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		cardapio.setData(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR));
		// DAO UPDATE
		CardapioDAO cardapioDao = cardapioDAOImpl;
		cardapioDao.updateCardapio(cardapio);
	}
	
	private static void testListCardapios(){
		CardapioDAO cardapioDao = cardapioDAOImpl;
		List<Cardapio> listCardapios = cardapioDao.listCardapios();
		for (Cardapio cardapio : listCardapios) {
			System.out.println(
					"\nId Cardapio: " + cardapio.getId() +
					"\nTitulo Cardapio: " + cardapio.getTitulo() + 
					"\nDescricao Cardapio: " + cardapio.getDescricao() + 
					"\nSetor Cardapio: " + cardapio.getSetor() + 
					"\nData Cardapio: " + cardapio.getData() + 
					"\nStatus Cardapio: " + cardapio.getStatus() +
					"\nValor Cardapio: " + cardapio.getValor()
			);
		}
	}
	
	private static void testGetCardapioById(int id){
		CardapioDAO cardapioDao = cardapioDAOImpl;
		Cardapio cardapio = cardapioDao.getCardapioById(id);
		System.out.println(
				"\nId Cardapio: " + cardapio.getId() +
				"\nTitulo Cardapio: " + cardapio.getTitulo() + 
				"\nDescricao Cardapio: " + cardapio.getDescricao() + 
				"\nSetor Cardapio: " + cardapio.getSetor() + 
				"\nData Cardapio: " + cardapio.getData() + 
				"\nStatus Cardapio: " + cardapio.getStatus() +
				"\nValor Cardapio: " + cardapio.getValor()
		);		
	}
	
	public static void testRemoveCardapio(int id){
		CardapioDAO cardapioDao = cardapioDAOImpl;
		cardapioDao.removeCardapio(id);
	}

}