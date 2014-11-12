package br.senai.sc.ti20131n.pw.gpe.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti20131n.pw.gpe.dao.ContatoDao;
import br.senai.sc.ti20131n.pw.gpe.entity.Contato;

@ManagedBean
public class ContatoMb {
	private ContatoDao ContatoDao;
	private List<Contato> listContatos;
	private Contato Contato;

	public List<Contato> getListContatos() {
		if (listContatos == null) {
			listContatos = ContatoDao.listar();
		}
		return listContatos;
	}

	@PostConstruct
	public void init() {
		ContatoDao = new ContatoDao(null);
		Contato = new Contato();
	}

	public String salvar() {
		ContatoDao.salvar(getContato());
		return "listContatos";
	}

	public String carregarEdicao(String id) {
		Contato = ContatoDao.buscarPorId(Long.parseLong(id));
		return "Contatoform";
	}

	public String excluir(String id) {
		ContatoDao.excluir(Long.parseLong(id));
		listContatos = null;
		return "listContatos";
	}

	// GET SET
	public Contato getContato() {
		return Contato;
	}

	public void setContato(Contato Contato) {
		this.Contato = Contato;
	}

	public ContatoDao getContatoDao() {
		return ContatoDao;
	}

	public void setContatoDao(ContatoDao ContatoDao) {
		this.ContatoDao = ContatoDao;
	}

	public void setListContatos(List<Contato> listContatos) {
		this.listContatos = listContatos;
	}

}