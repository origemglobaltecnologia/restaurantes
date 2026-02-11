package br.com.administracao.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.administracao.dao.CaixaDAO;
import br.com.administracao.dao.CaixaDAOImpl;
import br.com.administracao.model.Caixa;

public class CaixaSERVICEImpl implements CaixaSERVICE {

	static CaixaDAOImpl caixaDAOImpl = new CaixaDAOImpl();
	CaixaDAO caixaDao = caixaDAOImpl;
	Calendar c = Calendar.getInstance();
	
	@Override
	public boolean addCaixa(Caixa caixa) {
		// Manipulando os dados de data
		caixa.setDia(c.get(Calendar.DAY_OF_MONTH));
		caixa.setMes(c.get(Calendar.MONTH)+1);
		caixa.setAno(c.get(Calendar.YEAR));
		// Manipulando dados da hora
		caixa.setHora(c.get(Calendar.HOUR_OF_DAY));
		caixa.setMinuto(c.get(Calendar.MINUTE));
		caixa.setSegundo(c.get(Calendar.SECOND));
	
		Caixa cx = this.getCaixaByLastId();
		if(cx == null){
			this.caixaDao.addCaixa(caixa);	
			return true;
		}
		if(!cx.getStatus().matches("ABERTO")){
			this.caixaDao.addCaixa(caixa);	
			return true;
		}
		return false;
	}

	@Override
	public void updateCaixa(Caixa caixa) {
		// Manipulando os dados de data
		caixa.setDia(c.get(Calendar.DAY_OF_MONTH));
		caixa.setMes(c.get(Calendar.MONTH)+1);
		caixa.setAno(c.get(Calendar.YEAR));
		// Manipulando dados da hora
		caixa.setHora(c.get(Calendar.HOUR_OF_DAY));
		caixa.setMinuto(c.get(Calendar.MINUTE));
		caixa.setSegundo(c.get(Calendar.SECOND));
		this.caixaDao.updateCaixa(caixa);
	}

	@Override
	public List<Caixa> listCaixas() {
		return this.caixaDao.listCaixas();
	}

	@Override
	public Caixa getCaixaById(int id) {
		return this.caixaDao.getCaixaById(id);
	}
	
	@Override
	public Caixa getCaixaByLastId() {
		return this.caixaDao.getCaixaByLastId();
	}
	
	@Override
	public Caixa getCaixaByLastIdAberto() {
		Caixa caixa = this.caixaDao.getCaixaByLastId();
		if(caixa.getStatus().matches("ABERTO")){
			return this.caixaDao.getCaixaByLastId();
		} else {
			return null;
		}
	}

	@Override
	public void removeCaixa(int id) {
		this.caixaDao.removeCaixa(id);
	}
	
	@Override
	public void subtrairValorDoCaixa(int idCaixa, BigDecimal valorItem, BigDecimal comissaoItem) {
		Caixa caixa = this.getCaixaById(idCaixa);
		BigDecimal valorCaixa = caixa.getValor();
		if(comissaoItem != null && !comissaoItem.equals("0.00")){
			valorItem = valorItem.add(comissaoItem);
		}
		BigDecimal valorCaixaParaGravar = valorCaixa.subtract(valorItem);
		caixa.setValor(valorCaixaParaGravar);
		this.updateValorCaixa(caixa);
	}
	
	public void updateValorCaixa(Caixa caixa) {
		this.caixaDao.updateValorCaixa(caixa);
	}

}
