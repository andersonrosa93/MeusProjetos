package br.com.framework.service;

import java.util.List;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.ValidationException;

import br.com.framework.dao.EnderecoDAO;
import br.com.framework.dao.ProdutoDAO;
import br.com.framework.model.Endereco;
import br.com.framework.model.Produto;

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