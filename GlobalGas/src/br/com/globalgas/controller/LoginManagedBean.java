package br.com.globalgas.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import br.com.globalgas.dao.UsuarioDAO;
import br.com.globalgas.model.Usuario;

@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	public void envia() {

		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		FacesMessage message = null;
		boolean loggedIn = false;

		if (usuario != null) {
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem vindo", usuario.getNomeUsuario());
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro de registro", "Credenciais inválidas");
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);

		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			if (loggedIn) {
				
				ec.redirect(ec.getRequestContextPath() + "/faces/index2.xhtml");
				
			} else {
				
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}