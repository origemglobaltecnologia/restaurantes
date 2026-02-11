package br.com.administracao.service;

import java.util.Calendar;
import java.util.List;

import br.com.administracao.dao.CardapioDAO;
import br.com.administracao.dao.CardapioDAOImpl;
import br.com.administracao.model.Cardapio;

public class CardapioSERVICEImpl implements CardapioSERVICE {

	static CardapioDAOImpl cardapioDAOImpl = new CardapioDAOImpl();
	CardapioDAO cardapioDao = cardapioDAOImpl;
	Calendar calendar = Calendar.getInstance();
	
	@Override
	public void addCardapio(Cardapio c) {
		String data = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR);
		c.setData(data);
		this.cardapioDao.addCardapio(c);
	}

	@Override
	public void updateCardapio(Cardapio c) {
		String data = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR);
		c.setData(data);
		this.cardapioDao.updateCardapio(c);
	}

	@Override
	public List<Cardapio> listCardapios() {
		return this.cardapioDao.listCardapios();
	}

	@Override
	public Cardapio getCardapioById(int id) {
		return this.cardapioDao.getCardapioById(id);
	}

	@Override
	public void removeCardapio(int id) {
		this.cardapioDao.removeCardapio(id);
	}

}
