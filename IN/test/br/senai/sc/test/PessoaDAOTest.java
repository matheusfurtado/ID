package br.senai.sc.test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import br.senai.sc.ti20131n.pw.gpe.entity.Pessoa;

public class PessoaDAOTest extends DBUnitTest {

	public PessoaDAOTest() {
		super();
	}

	private void gravaPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Nome_Teste_01");
		pessoa.setCpf("CPF_Teste_01");
		pessoa.setDataNascimento("Data_Teste_01");
		pessoa.setEmail("Email_Teste_01");
		pessoa.setUsuario("Usuario_Teste_01");
		pessoa.setSenha("Senha_Teste_01");
		pessoaDao.salvar(pessoa);

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Nome_Teste_02");
		pessoa2.setCpf("CPF_Teste_02");
		pessoa2.setDataNascimento("Data_Teste_02");
		pessoa2.setEmail("Email_Teste_02");
		pessoa2.setUsuario("Usuario_Teste_02");
		pessoa2.setSenha("Senha_Teste_02");
		pessoaDao.salvar(pessoa2);

		Pessoa pessoa3 = new Pessoa();
		pessoa3.setNome("Nome_Teste_03");
		pessoa3.setCpf("CPF_Teste_03");
		pessoa3.setDataNascimento("Data_Teste_03");
		pessoa3.setEmail("Email_Teste_03");
		pessoa3.setUsuario("Usuario_Teste_03");
		pessoa3.setSenha("Senha_Teste_03");
		pessoaDao.salvar(pessoa3);
	}

	@Test
	public void testBandaDao() throws SQLException, Exception {
		beginPessoa();
		gravaPessoa();
		closePessoa();

		// Carregamento do estado atual do banco de dados.
		IDataSet dataBase = getConnection().createDataSet();
		ITable tabelaAtual = dataBase.getTable("pessoa");

		// Carregamento do arquivo de controle (pessoa.xml)
		IDataSet dataBaseXML = new FlatXmlDataSetBuilder()
				.build(new FileInputStream(new File("control/pessoa.xml")));
		ITable tabelaControle = dataBaseXML.getTable("pessoa");

		Assertion.assertEquals(tabelaControle, tabelaAtual);

	}

}
