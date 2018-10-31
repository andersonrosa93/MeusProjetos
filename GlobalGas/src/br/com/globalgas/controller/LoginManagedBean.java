package br.com.globalgas.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import br.com.globalgas.dao.UsuarioDAO;
import br.com.globalgas.model.Usuario;

@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	public String envia() {

		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());

		FacesContext context = FacesContext.getCurrentInstance();

		if (usuario != null) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo", usuario.getNomeUsuario()));

			context.getExternalContext().getSessionMap().put("logado", true);

			return "/restrito/index.xhtml?faces-redirect=true";
		} else {

			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));

			context.validationFailed();

			context.getExternalContext().getSessionMap().put("logado", false);
			context.getExternalContext().getFlash().setKeepMessages(true);

			return "/login/login.xhtml?faces-redirect=true";
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}