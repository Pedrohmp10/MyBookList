package br.edu.ctup.mybooklist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ctup.mybooklist.dal.AdministradorDAO;
import br.edu.ctup.mybooklist.model.Administrador;

@SessionScoped
@ManagedBean(name = "mAdministradorBean")
public class MAdministradorBean {

	private Administrador adm = new Administrador();
	private AdministradorDAO administradorDAO = new AdministradorDAO();
	private List<Administrador> administradores = new ArrayList<Administrador>();

	public List<Administrador> getAdministradores() {
		return AdministradorDAO.retornarAdministradores();
	}

	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.administradores = administradores;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public Administrador getAdm() {
		return adm;
	}

	public String addAdministrador(Administrador d) {
		adm = administradorDAO.buscarAdmPorMatricula(adm.getMatricula());
		if (adm != null) {
			adm = new Administrador();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADM já existente!", "Erro no Cadastro!"));
			return null;
		} else {
			AdministradorDAO.AddAdministrador(d);
			adm = new Administrador();
			return "LoginAdministrador.xhtml?faces-redirect=true";
		}
	}

	public String removerAdministrador(Administrador a) {
		AdministradorDAO.removerAdministrador(a);
		return "#";
	}

	public String enviarDadosParaAlterar(Administrador d) {
		this.adm = d;
		return "AltAdministrador.xhtml?faces-redirect=true";
	}

	public String alterarAdministrador(Administrador d) {
		AdministradorDAO.alterarAdministrador(d);
		adm = new Administrador();
		return "LoginAdministrador.xhtml?faces-redirect=true";
	}

	public String buscarAdministrador() {
		adm = AdministradorDAO.buscarAdmPorMatricula(adm.getMatricula());
		if (adm == null) {
			adm = new Administrador();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Matrícula não encontrada!", "Informe uma matrícula válida!"));
			return null;
		} else {
			return "AltAdministrador.xhtml?faces-redirect=true";
		}

	}

	public String envia() {
		adm = administradorDAO.getAdministrador(adm.getMatricula(), adm.getSenha());
		if (adm == null) {
			adm = new Administrador();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADM não encontrado!", "Erro no Login"));
			return null;
		} else {
			return "PerfilAdministrador.xhtml?faces-redirect=true";
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "LoginAdministrador.xhtml?faces-redirect=true";
	}
}
