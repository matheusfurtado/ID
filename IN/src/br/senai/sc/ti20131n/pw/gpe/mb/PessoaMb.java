package br.senai.sc.ti20131n.pw.gpe.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti20131n.pw.gpe.dao.PessoaDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Pessoa;

@ManagedBean
public class PessoaMb {
	private PessoaDao pessoaDao;
	private List<Pessoa> listPessoas;
	private Pessoa pessoa;

	public List<Pessoa> getListPessoas() {
		if (listPessoas == null) {
			listPessoas = pessoaDao.listar();
		}
		return listPessoas;
	}

	@PostConstruct
	public void init() {
		pessoaDao = new PessoaDao(null);
		pessoa = new Pessoa();
	}

	public String salvar() {
		pessoaDao.salvar(getPessoa());
		return "listPessoas";
	}

	public String carregarEdicao(String id) {
		pessoa = pessoaDao.buscarPorId(Long.parseLong(id));
		return "pessoaform";
	}

	public String excluir(String id) {
		pessoaDao.excluir(Long.parseLong(id));
		listPessoas = null;
		return "listPessoas";
	}

	// GET SET
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaDao getPessoaDao() {
		return pessoaDao;
	}

	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}

	public void setListPessoas(List<Pessoa> listPessoas) {
		this.listPessoas = listPessoas;
	}

}
