package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente Cliente) {
		this.em.persist(Cliente);
	}
	
	public Cliente buscasPorId (Long id) {
		return em.find(Cliente.class, id);
	}
	
	
}//fechamento ProdutoDao
