package br.com.framework.dao;


import br.com.framework.model.Produto;

import javax.persistence.*;
import java.util.List;

public class ProdutoDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-jpf-login");
	private EntityManager em = factory.createEntityManager();
	private EntityTransaction transaction = em.getTransaction();


	public void alterarProduto(Produto produto) {
		transaction.begin();
		em.merge(produto);
		transaction.commit();
	}


	public boolean inserirProduto(Produto produto) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.persist(produto);
			em.flush();
			transaction.commit();
			return true;
		} catch (Exception e) {
			// essa exception n�o � necessariamente um erro
			e.printStackTrace();
			return false;
		}
	}

	public Produto getProduto(int id) {
		try {
			return em.find(Produto.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean deletarProduto(Produto produto) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.merge(produto);
			em.remove(produto);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List listarProduto() {

		Query queryObj = em.createQuery("SELECT u FROM Produto u");
		List produtosList = queryObj.getResultList();
		if (produtosList != null && produtosList.size() > 0) {
			return produtosList;
		} else {
			return null;
		}

	}

}