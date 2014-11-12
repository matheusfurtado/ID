package br.senai.sc.ti20131n.pw.gpe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.senai.sc.ti20131n.pw.gpe.entity.Pessoa;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class PessoaDao {

	private EntityManager entityManager;

	public PessoaDao(EntityManager entityManager2) {
		entityManager = JpaUtil.getEntityManager();
	}

	public List<Pessoa> listar() {
		Query query = entityManager.createQuery("From Pessoa", Pessoa.class);
		return query.getResultList();
	}

	public void salvar(Pessoa pessoa) {
		entityManager.merge(pessoa);
	}

	public Pessoa buscarPorId(Long id) {
		return entityManager.find(Pessoa.class, id);
	}

	public Pessoa excluir(Long id) {
		Pessoa pessoa = entityManager.find(Pessoa.class, id);
		entityManager.remove(pessoa);
		return pessoa;
	}

}
