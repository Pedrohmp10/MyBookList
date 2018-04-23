package br.edu.ctup.mybooklist.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ctup.mybooklist.dal.GeneroDAO;
import br.edu.ctup.mybooklist.model.Genero;

@SessionScoped
@ManagedBean(name = "mGeneroBean")
public class MGeneroBean {

	private Genero genero = new Genero();
	private List<Genero> generos = new ArrayList<Genero>();

	public List<Genero> getGeneros() {
		return GeneroDAO.retornaGeneros();
	}
	public void setGeneros(List<Genero> generos) {
		this.generos = generos ;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero =  genero ;
	}

	public String addGenero(Genero g) {
		GeneroDAO.addGenero(g);
		genero = new Genero();
		return "CadGenero.xhtml?faces-redirect=true";
	}

	public String removerGenero(Genero g){
		GeneroDAO.removerGenero(g);
		genero = new Genero();
		return "ListGenero.xhtml?faces-redirect=true";
	}
	
	public String enviarDadosParaAlterar(Genero g){
		this.genero = g;
		return "AltGenero.xhtml?faces-redirect=true";
	}
	
	public String alterarGenero(Genero g){
		GeneroDAO.alterarGenero(g);
		genero = new Genero();
		return "ListGenero.xhtml?faces-redirect=true";
	}

}
