package br.com.administracao.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.administracao.dao.CaixaDAO;
import br.com.administracao.dao.CaixaDAOImpl;
import br.com.administracao.model.Caixa;

public class TestCaixaDAO {

	static CaixaDAOImpl caixaDAOImpl = new CaixaDAOImpl();

	public static void main(String[] args) {
		testAddCaixa();
		testUpdateCaixa();
		testListCaixas();
		testGetCaixaById(4);
		testRemoveCaixa(4);
	}

	public static void testAddCaixa(){
		Caixa caixa = new Caixa();
		caixa.setUsuario(1);
		caixa.setValor(new BigDecimal(0.00));
		caixa.setStatus("FECHADO");
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		caixa.setDia(c.get(Calendar.DAY_OF_MONTH));
		caixa.setMes(c.get(Calendar.MONTH)+1);
		caixa.setAno(c.get(Calendar.YEAR));
		// Manipulando dados da hora
		caixa.setHora(c.get(Calendar.HOUR_OF_DAY));
		caixa.setMinuto(c.get(Calendar.MINUTE));
		caixa.setSegundo(c.get(Calendar.SECOND));
		// DAO INSERT
		CaixaDAO caixaDao = caixaDAOImpl;
		caixaDao.addCaixa(caixa);
	}
	
	public static void testUpdateCaixa(){
		Caixa caixa = new Caixa();
		caixa.setId(5);
		caixa.setUsuario(1);
		BigDecimal valor = new BigDecimal(10.99);
		caixa.setValor(valor);
		caixa.setStatus("FECHADO");
		// Manipulando os dados de data
		Calendar c = Calendar.getInstance();
		caixa.setDia(c.get(Calendar.DAY_OF_MONTH));
		caixa.setMes(c.get(Calendar.MONTH)+1);
		caixa.setAno(c.get(Calendar.YEAR));
		// Manipulando dados da hora
		caixa.setHora(c.get(Calendar.HOUR_OF_DAY));
		caixa.setMinuto(c.get(Calendar.MINUTE));
		caixa.setSegundo(c.get(Calendar.SECOND));
		// DAO INSERT
		CaixaDAO caixaDao = caixaDAOImpl;
		caixaDao.updateCaixa(caixa);
	}
	
	private static void testListCaixas(){
		CaixaDAO caixaDao = caixaDAOImpl;
		List<Caixa> listCaixas = caixaDao.listCaixas();
		for (Caixa caixa : listCaixas) {
			System.out.println(
					"\nId Caixa: " + caixa.getId() +
					"\nDia Caixa: " + caixa.getDia() +
					"\nMes Caixa: " + caixa.getMes() +
					"\nAno Caixa: " + caixa.getAno() +
					"\nHora Caixa: " + caixa.getHora() +
					"\nMinuto Caixa: " + caixa.getMinuto() +
					"\nSegundo Caixa: " + caixa.getSegundo() + 
					"\nStatus Caixa: " + caixa.getStatus() +
					"\nUsuario Caixa: " + caixa.getUsuario() +
					"\nValor Caixa: " + caixa.getValor()
			);
		}
	}
	
	private static void testGetCaixaById(int id){
		CaixaDAO caixaDao = caixaDAOImpl;
		Caixa caixa = caixaDao.getCaixaById(id);
		System.out.println(
				"\nDia Caixa: " + caixa.getDia() +
				"\nMes Caixa: " + caixa.getMes() +
				"\nAno Caixa: " + caixa.getAno() +
				"\nHora Caixa: " + caixa.getHora() +
				"\nMinuto Caixa: " + caixa.getMinuto() +
				"\nSegundo Caixa: " + caixa.getSegundo() + 
				"\nStatus Caixa: " + caixa.getStatus() +
				"\nUsuario Caixa: " + caixa.getUsuario() +
				"\nValor Caixa: " + caixa.getValor()
		);		
	}
	
	public static void testRemoveCaixa(int id){
		CaixaDAO caixaDao = caixaDAOImpl;
		caixaDao.removeCaixa(id);
	}

}