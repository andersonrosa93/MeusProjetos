package br.com.globalgas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.globalgas.model.Funcionario;

public class FuncionarioDao {
	
	
	
	EntityManager entityManager;

	// Nossa classe PessoaDao segue o padr�o de projeto
	// Singleton que garante que apenas uma instancia dessa
	// classe ser� criada durante toda a aplica��o

	private static FuncionarioDao instance;

	public static FuncionarioDao getInstance() {
		if (instance == null) {
			instance = new FuncionarioDao();
		}

		return instance;
	}

	private FuncionarioDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("GlobalGas");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();

		}

		return entityManager;
	}

	public void persist(Funcionario funcionario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(funcionario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}

	}

}