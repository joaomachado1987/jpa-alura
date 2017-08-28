package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TestConta {
	public static void main(String[] args) {
		Conta conta = new Conta();
		
		conta.setAgencia("agencia");
		conta.setNumero("numero");
		conta.setConta("conta");
		conta.setTitular("titular");
				
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		
		em.clear();
	}
}
