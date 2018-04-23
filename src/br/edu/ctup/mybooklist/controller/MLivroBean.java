package br.edu.ctup.mybooklist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ctup.mybooklist.dal.AutorDAO;
import br.edu.ctup.mybooklist.dal.GeneroDAO;
import br.edu.ctup.mybooklist.dal.LivroDAO;
import br.edu.ctup.mybooklist.model.Autor;
import br.edu.ctup.mybooklist.model.Genero;
import br.edu.ctup.mybooklist.model.Livro;

@SessionScoped
@ManagedBean(name = "mLivroBean")
public class MLivroBean {

	private Livro livro = new Livro();
	private List<Livro> livros = new ArrayList<Livro>();
	private LivroDAO livroDAO = new LivroDAO();
	private int idAutor;
	private int idGenero;

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getLivros() {
		return LivroDAO.retornaLivros();
	}

	public void setLivros(ArrayList<Livro> livros) {
		this.livros = livros;
	}

	public String addLivro(Livro l) {
		livro = livroDAO.buscarLivroPorcod(livro.getCod());

		if (livro != null) {
			livro = new Livro();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Código do Livro já existente!", "Erro no Cadastro!"));
			return null;
		} else {

			Genero g = GeneroDAO.buscarGeneroPorId(idGenero);
			Autor a = AutorDAO.buscarAutorPorId(idAutor);
			l.setGenero(g);
			l.setAutor(a);
			LivroDAO.addLivro(l);
			livro = new Livro();
			return "CadLivro.xhtml?faces-redirect=true";
		}
	}

	public String removerLivro(Livro l) {
		LivroDAO.removerLivro(l);
		livro = new Livro();
		return "ListLivro.xhtml?faces-redirect=true";
	}

	public String enviarDadosParaAlterar(Livro l) {
		this.livro = l;
		return "AltLivro.xhtml?faces-redirect=true";
	}

	public String alterarLivro(Livro l) {
		Genero g = GeneroDAO.buscarGeneroPorId(idGenero);
		Autor a = AutorDAO.buscarAutorPorId(idAutor);
		l.setGenero(g);
		l.setAutor(a);
		LivroDAO.alterarLivro(l);
		livro = new Livro();
		return "ListLivro.xhtml?faces-redirect=true";
	}

	public String buscarLivro() {
		livro = LivroDAO.buscarLivroPorNome(livro.getNome());
		if (livro == null) {
			livro = new Livro();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro não encontrado!", "Erro na Busca!"));
			return null;
		} else {
			return "VerLivro.xhtml?faces-redirect=true";
		}
	}

}
