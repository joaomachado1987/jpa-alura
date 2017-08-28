package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestBuscaConta {
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setId(3); // conta com a Id 3 já foi persistida anteriormente
		conta.setTitular("Django");
		conta.setBanco("a");
		conta.setNumero("123");
		conta.setAgencia("3344");        

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
//		conta = em.find(Conta.class, 3);

		em.persist(conta);

		em.getTransaction().commit();
		em.close();
	}
}
