package br.edu.ctup.mybooklist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import br.edu.ctup.mybooklist.dal.EmprestimoDAO;
import br.edu.ctup.mybooklist.dal.LivroDAO;
import br.edu.ctup.mybooklist.dal.UsuComumDAO;
import br.edu.ctup.mybooklist.model.Emprestimo;
import br.edu.ctup.mybooklist.model.Livro;
import br.edu.ctup.mybooklist.model.UsuComum;

@ManagedBean(name = "mEmprestimoBean")
public class MEmprestimoBean {
	
	private Emprestimo emprestimo = new Emprestimo();
	private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
	private int idLivro;
	private int nUsuComum;
	
	

	

	public int getnUsuComum() {
		return nUsuComum;
	}
	public void setnUsuComum(int nUsuComum) {
		this.nUsuComum = nUsuComum;
	}
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	public List<Emprestimo> getEmprestimos(){
		return EmprestimoDAO.retornarEmprestimos();
	}
	public void setEmprestimos(List<Emprestimo> emprestimos){
		this.emprestimos = emprestimos;
	}
	public Emprestimo getEmprestimo(){
		return emprestimo;
	}
	public void setEmprestimo(Emprestimo emprestimo){
		this.emprestimo = emprestimo;
	}
	
	public String realizarEmprestimo(Emprestimo e){
		Livro  l = LivroDAO.buscarLivroPorId(idLivro);
		UsuComum u = UsuComumDAO.buscarUsuComumPorId(nUsuComum);
		e.setUsuComum(u);
		e.setLivro(l);
		EmprestimoDAO.realizarEmprestimo(e);
		emprestimo = new Emprestimo();
		return"Emprestimo.xhtml?faces-redirect=true";
	}
	
	public String DevolverLivro(Emprestimo emp){
		EmprestimoDAO.devolverLivro(emp);
		emprestimo = new Emprestimo();
		return "ListEmprestimo.xhtml?faces-redirect=true";
	}
	

}
