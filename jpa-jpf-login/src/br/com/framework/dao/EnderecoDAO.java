package br.com.framework.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.framework.model.Endereco;
import br.com.framework.model.Usuario;

public class EnderecoDAO {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-jpf-login");
	private EntityManager em = factory.createEntityManager();
	private EntityTransaction transaction = em.getTransaction();


	public void alterarEndereco(Endereco endereco) {
		transaction.begin();
		em.merge(endereco);
		transaction.commit();
	}


	public boolean inserirEndereco(Endereco endereco) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			Usuario usuario = em.find(Usuario.class, endereco.getUsuario().getId());
			endereco.setUsuario(usuario);
			usuario.setEndereco(endereco);
			em.persist(endereco);
			em.flush();
			transaction.commit();
			return true;
		} catch (Exception e) {
			// essa exception n�o � necessariamente um erro
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarEndereco(Endereco endereco) {
		if (!transaction.isActive()) {
			transaction.begin();
		}

		try {
			em.merge(endereco);
			em.remove(endereco);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List listarEndereco() {

		Query queryObj = em.createQuery("SELECT u FROM Endereco u");
		List enderecosList = queryObj.getResultList();
		if (enderecosList != null && enderecosList.size() > 0) {
			return enderecosList;
		} else {
			return null;
		}

	}

}