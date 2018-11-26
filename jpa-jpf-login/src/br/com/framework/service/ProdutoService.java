package br.com.framework.service;


import br.com.framework.dao.ProdutoDAO;
import br.com.framework.model.Produto;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.ValidationException;
import java.util.List;

@ManagedBean(name = "produtoService")
@ApplicationScoped
public class ProdutoService {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	
	public void salvarProduto(Produto produto) throws ValidationException{
		produtoDAO.inserirProduto(produto);
	}
	
	public void editarProduto(Produto produto) throws ValidationException{
		produtoDAO.alterarProduto(produto);
	}
	
	public List listarProduto() {
		return produtoDAO.listarProduto();
	}
	
	public void deletarProduto(Produto produto){
		produtoDAO.deletarProduto(produto);
	}
}
