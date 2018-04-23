package br.edu.ctup.mybooklist.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ctup.mybooklist.dal.AutorDAO;
import br.edu.ctup.mybooklist.model.Autor;

@SessionScoped
@ManagedBean(name = "mAutorBean")
public class MAutorBean {
	
	private Autor autor = new Autor();
	private List<Autor> autores = new ArrayList<Autor>();

	public List<Autor> getAutores(){
		return AutorDAO.retornaAutores();
	}
	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor =  autor;
	}

	public String addAutor(Autor a){
		AutorDAO.addAutor(a);
		autor = new Autor();
		return "CadAutor.xhtml?faces-redirect=true";
	}

	public String removerAutor(Autor a){
		AutorDAO.removerAutor(a);
		autor = new Autor();
		return "ListAutor.xhtml?faces-redirect=true";
	}
	
	public String enviarDadosParaAlterar(Autor a){
		this.autor = a;
		return "AltAutor.xhtml?faces-redirect=true";
	}
	
	public String alterarAutor(Autor a){
		AutorDAO.alterarAutor(a);
		autor = new Autor();
		return "ListAutor.xhtml?faces-redirect=true";
	}

}
