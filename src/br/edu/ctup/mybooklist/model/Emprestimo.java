package br.edu.ctup.mybooklist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Emprestimos")
public class Emprestimo {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String data;
	private String endereco;
	@ManyToOne 
	private Livro livro;
	@ManyToOne 
	private UsuComum usuComum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public UsuComum getUsuComum() {
		return usuComum;
	}
	public void setUsuComum(UsuComum usuComum) {
		this.usuComum = usuComum;
	}	
	

}
