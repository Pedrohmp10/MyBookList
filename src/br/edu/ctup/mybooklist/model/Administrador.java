package br.edu.ctup.mybooklist.model;

import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "Administradores")
public class Administrador extends Usuario {


	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	


}
