package br.senai.sc.ti20131n.pw.gpe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Anuncio;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class AnuncioDao {

	private EntityManager entityManager;

	public AnuncioDao(EntityManager entityManager2) {
		entityManager = JpaUtil.getEntityManager();
	}

	public List<Anuncio> listar() {
		Query query = entityManager.createQuery("From Anuncio", Anuncio.class);
		return query.getResultList();
	}

	public void salvar(Anuncio anuncio) {
		entityManager.merge(anuncio);
	}

	public Anuncio buscarPorId(Long id) {
		return entityManager.find(Anuncio.class, id);
	}

	public Anuncio excluir(Long id) {
		Anuncio anuncio = entityManager.find(Anuncio.class, id);
		entityManager.remove(anuncio);
		return anuncio;
	}

}
