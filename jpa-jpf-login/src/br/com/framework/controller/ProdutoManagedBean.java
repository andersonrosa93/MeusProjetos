package br.com.framework.controller;

import br.com.framework.model.Produto;

import br.com.framework.service.ProdutoService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ValidationException;
import java.util.List;

@ManagedBean
@SessionScoped
public class ProdutoManagedBean {

	private final String TELA_NOVO_PRODUTO = "/restrito/produto/novoProduto.xhtml?faces-redirect=true";
	private final String TELA_LISTAGEM_PRODUTO = "/restrito/endereco/listagemEndereco.xhtml?faces-redirect=true";
	private final String TELA_PRODUTO_EDITAR = "/restrito/endereco/editarEndereco.xhtml?faces-redirect=true&id=";
	
	
	@ManagedProperty("#{produtoService}")
	private ProdutoService produtoService;
	private Produto produto = new Produto();
	
	public List produtoListDb() {
		return getProdutoService().listarProduto();
	}
	
	
	public void excluirProdutoDb(Produto produto) {
		getProdutoService().deletarProduto(produto);
	}
	
	public String incluirProdutoDb(Produto produto) {
		try {
			getProdutoService().salvarProduto(produto);
			
			return TELA_LISTAGEM_PRODUTO;
		}catch(Exception e) {
			e.printStackTrace();
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			return TELA_NOVO_PRODUTO;
		}
	}
	
	public String editarProdutoDb(Produto produto) {
		try {
			getProdutoService().editarProduto(produto);
		}catch (ValidationException e) {
			e.printStackTrace();
		}
		
		return TELA_LISTAGEM_PRODUTO;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
