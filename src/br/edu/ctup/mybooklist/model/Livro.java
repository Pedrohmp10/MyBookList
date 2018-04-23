package br.edu.ctup.mybooklist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int    id;
	private int    cod;
	private String nome;
	@ManyToOne 
	private Genero genero;
	@ManyToOne
	private Autor  autor;
	private String dataLan;
	private int    qtdePagL;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public String getDataLan() {
		return dataLan;
	}
	public void setDataLan(String dataLan) {
		this.dataLan = dataLan;
	}
	public int getQtdePagL() {
		return qtdePagL;
	}
	public void setQtdePagL(int qtdePagL) {
		this.qtdePagL = qtdePagL;
	}
	
	
	
}
