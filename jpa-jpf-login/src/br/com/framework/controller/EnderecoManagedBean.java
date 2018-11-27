package br.com.framework.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;

import br.com.framework.model.Endereco;
import br.com.framework.model.Usuario;
import br.com.framework.service.EnderecoService;

@ManagedBean
@SessionScoped
public class EnderecoManagedBean {

	private final String TELA_NOVO_ENDERECO = "/restrito/endereco/novoEndereco.xhtml?faces-redirect=true";
	private final String TELA_LISTAGEM_ENDERECO = "/restrito/endereco/listagemEndereco.xhtml?faces-redirect=true";
	private final String TELA_ENDERECO_EDITAR = "/restrito/endereco/editarEndereco.xhtml?faces-redirect=true&id=";


	@ManagedProperty("#{enderecoService}")
	private EnderecoService enderecoService;
	private Endereco endereco;
	private Usuario usuario;

	public List enderecoListDb() {
		return getEnderecoService().listarEndereco();
	}


	public void excluirEnderecoDb(Endereco endereco) {
		getEnderecoService().deletarEndereco(endereco);
	}

	public String incluirEnderecoDb(Endereco endereco) {
		try {
			endereco.setUsuario(usuario);
			getEnderecoService().salvarEndereco(endereco);

			return TELA_LISTAGEM_ENDERECO;
		}catch(Exception e) {
			e.printStackTrace();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			context.getExternalContext().getFlash().setKeepMessages(true);

			return TELA_NOVO_ENDERECO;
		}
	}

	public String editarEnderecoDb(Endereco endereco) {
		try {
			getEnderecoService().editarEndereco(endereco);
		}catch (ValidationException e) {
			e.printStackTrace();
		}

		return TELA_LISTAGEM_ENDERECO;
	}

	public String paginaEndereco(Usuario usuario) {
		String tela = "";
		this.usuario = usuario;
		if(usuario.getEndereco() != null) {
			setEndereco(usuario.getEndereco());
			tela = TELA_ENDERECO_EDITAR + usuario.getId();
		}else {
			this.setEndereco(new Endereco());
			tela = TELA_NOVO_ENDERECO + usuario.getId();
		}

		return tela;
	}






	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public EnderecoService getEnderecoService() {
		return enderecoService;
	}


	public void setEnderecoService(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


}