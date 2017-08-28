package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class MovimentacaoTeste {
	public static void main(String[] args) {
		Movimentacao movimentacao = new Movimentacao();
		Conta conta = new Conta();
		conta.setAgencia("123465");
		conta.setBanco("Bradesco");
		conta.setConta("123654");
		conta.setTitular("Lisca");
		
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("200"));
		movimentacao.setConta(conta);
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(conta);
		entityManager.persist(movimentacao);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
