package br.com.administracao.dao;


import java.util.List;

import br.com.administracao.model.Cardapio;

public interface CardapioDAO {

	public void addCardapio(Cardapio c);
	public void updateCardapio(Cardapio c);
	public List<Cardapio> listCardapios();
	public Cardapio getCardapioById(int id);
	public void removeCardapio(int id);
	
}
