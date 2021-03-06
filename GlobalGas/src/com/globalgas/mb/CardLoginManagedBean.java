package com.globalgas.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.globalgas.dao.UsuarioDAO;
import com.globalgas.model.Usuario;

@ManagedBean(name = "cardLoginMB")
@ViewScoped
public class CardLoginManagedBean {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	private Usuario usuario = new Usuario();

	public String enviar() {

		apiRecebeDadosCartao();

		usuario = usuarioDAO.getUsuarioCard(usuario.getNomeUsuario(), usuario.getMatricula());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cartão não aceito!", "Erro no Login!"));
			return null;
		} else {
			return "/acessoLiberado";
		}
	}

	private void apiRecebeDadosCartao() {
		String nomeUsuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("nomeUsuario");

		String matricula = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("matricula");

		usuario.setNomeUsuario(nomeUsuario);
		usuario.setMatricula(matricula);

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}