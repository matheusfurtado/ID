package br.senai.sc.test;

import java.io.File;
import java.io.FileInputStream;

import javax.persistence.EntityManager;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import br.senai.sc.ti20131n.pw.gpe.dao.AnuncioDao;
import br.senai.sc.ti20131n.pw.gpe.dao.PessoaDao;
import br.senai.sc.ti20131n.pw.gpe.util.JpaUtil;

public class DBUnitTest extends DBTestCase {

	private EntityManager entityManager;
	public AnuncioDao anuncioDao;
	public PessoaDao pessoaDao;

	public DBUnitTest() {
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				"com.mysql.jdbc.Driver");
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:mysql://localhost:3306/usuario");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				"root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				"");
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(new File(
				"input/dbZerado.xml")));
	}

	@Override
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

	public void beginAnuncio() {
		JpaUtil.initFactory();
		entityManager = JpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		anuncioDao = new AnuncioDao(entityManager);
	}

	public void closeAnuncio() {
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManager = null;
		anuncioDao = null;
		JpaUtil.closeFactory();
	}

	public void beginPessoa() {
		JpaUtil.initFactory();
		entityManager = JpaUtil.getEntityManager();
		entityManager.getTransaction().begin();
		pessoaDao = new PessoaDao(entityManager);
	}

	public void closePessoa() {
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManager = null;
		pessoaDao = null;
		JpaUtil.closeFactory();
	}

}
