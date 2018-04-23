package br.edu.ctup.mybooklist.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UsuComums")
public class UsuComum extends Usuario{
	

	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


}
