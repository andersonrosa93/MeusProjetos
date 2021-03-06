package com.globalgas.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.globalgas.model.Endereco;
import com.globalgas.model.Usuario;
import com.globalgas.service.EnderecoService;

@ManagedBean
@SessionScoped
public class EnderecoManagedBean {

	private final String TELA_NOVO_ENDERECO = "/restrito/endereco/novoEndereco.xhtml?faces-redirect=true";
	private final String TELA_LISTAGEM_ENDERECO = "/restrito/endereco/listagemEnderecoT?faces-redirect=true";
	private final String TELA_ENDERECO = "/restrito/endereco/novoEnderecoT?faces-redirect=true&id=";

	private Endereco endereco = new Endereco();
	private EnderecoService enderecoService = new EnderecoService();
	private Usuario usuario = new Usuario();

	public List enderecoListDb() {
		return enderecoService.listarEndereco();
	}

	public void exlcluirEnderecoDb(Endereco endereco) {
		 enderecoService.deletarEndereco(endereco);
	}

	public String incluirEnderecoDb(Endereco endereco) {
		try {
			endereco.setUsuario(usuario);
			enderecoService.salvarEndereco(endereco);
			return TELA_LISTAGEM_ENDERECO;
		} catch (Exception e) {
			e.printStackTrace();

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			context.getExternalContext().getFlash().setKeepMessages(true);

			return TELA_NOVO_ENDERECO;
		}
	}

	public String editarEnderecoDb(Endereco endereco) {
		// produtoDAO.alterarEndereco();
		return "/restrito/listagemProdutoT?faces-redirect=true";
	}

	public String paginaEndereco(Usuario usuario) {
		this.usuario = usuario;
		this.endereco = new Endereco();

		return TELA_ENDERECO + usuario.getId();
	}

	public Endereco getProduto() {
		return getEndereco();
	}
	

	public void setProduto(Endereco produto) {
		this.setEndereco(produto);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
