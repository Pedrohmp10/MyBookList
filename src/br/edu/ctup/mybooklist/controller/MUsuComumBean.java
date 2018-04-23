package br.edu.ctup.mybooklist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.edu.ctup.mybooklist.dal.UsuComumDAO;
import br.edu.ctup.mybooklist.model.UsuComum;

@SessionScoped
@ManagedBean(name = "mUsuComumBean")
public class MUsuComumBean {

	private UsuComum usuComum = new UsuComum();
	private UsuComumDAO usuComumDAO = new UsuComumDAO();
	private List<UsuComum> usersC = new ArrayList<UsuComum>();

	public List<UsuComum> getUsersC() {
		return UsuComumDAO.retornaUsersC();
	}

	public void setUsersC(List<UsuComum> usersC) {
		this.usersC = usersC;
	}

	public UsuComum getUsuComum() {
		return usuComum;
	}

	public void setUsuComum(UsuComum usuComum) {
		this.usuComum = usuComum;
	}

	public String addUsuComum(UsuComum u) {

		usuComum = usuComumDAO.buscaruser(usuComum.getUser());
		if (usuComum != null) {
			usuComum = new UsuComum();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Usuario já existente!", "Erro no Cadastro!"));
			return null;
		} else {
			UsuComumDAO.addUsuComum(u);
			usuComum = new UsuComum();
			return "Inicio.xhtml?faces-redirect=true";
		}
		
	}

	public String removerUsuComum(UsuComum u) {
		UsuComumDAO.removerUsuComum(u);
		usuComum = new UsuComum();
		return "ListUsuComum.xhtml?faces-redirect=true";
	}

	public String enviarDadosParaAlterar(UsuComum u) {
		this.usuComum = u;
		return "AltUsuComum.xhtml?faces-redirect=true";
	}

	public String enviarDadosParaAlterarUsuario(UsuComum u) {
		this.usuComum = u;
		return "AltUsuario.xhtml?faces-redirect=true";
	}

	public String alterarUsuComum(UsuComum u) {
		UsuComumDAO.alterarUsuComum(u);
		usuComum = new UsuComum();
		return "ListUsuComum.xhtml?faces-redirect=true";
	}

	public String alterarUsuario(UsuComum u) {
		UsuComumDAO.alterarUsuComum(u);
		usuComum = new UsuComum();
		return "Inicio.xhtml?faces-redirect=true";
	}

	public String buscarUsuComum() {
		usuComum = usuComumDAO.buscarUsuComumPorSenha(usuComum.getSenha());
		if (usuComum == null) {
			usuComum = new UsuComum();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Senha não encontrada!", "Informe sua senha correta!"));
			return null;
		} else {
			return "AltUsuario.xhtml?faces-redirect=true";
		}
	}

	public String envia() {
		usuComum = usuComumDAO.getUsuComum(usuComum.getUser(), usuComum.getSenha());
		if (usuComum == null) {
			usuComum = new UsuComum();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login"));
			return null;
		} else {
			return "PerfilUsuComum.xhtml?faces-redirect=true";

		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Inicio.xhtml?faces-redirect=true";
	}
}
