package br.com.framework.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.framework.dao.UsuarioDAO;
import br.com.framework.model.Usuario;

@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginManagedBean {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	public String enviar() {
		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		FacesContext context = FacesContext.getCurrentInstance();
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usu�rio n�o encontrado!", "Erro no Login!"));
			FacesContext.getCurrentInstance().validationFailed();
			
			context.getExternalContext().getSessionMap().put("logado", false);
			 
			return null;
		} else {
			context.getExternalContext().getSessionMap().put("logado", true);
			return "/restrito/listagemUsuario.xhtml?faces-redirect=true";
		}
	}

	// Realiza o logout do usu�rio logado
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("logado");
		return "/login/login.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}