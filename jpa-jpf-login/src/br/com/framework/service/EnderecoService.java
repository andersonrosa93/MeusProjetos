package br.com.framework.service;

import java.util.List;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.ValidationException;

import br.com.framework.dao.EnderecoDAO;
import br.com.framework.model.Endereco;

@ManagedBean(name = "enderecoService")
@ApplicationScoped
public class EnderecoService {

	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	public void salvarEndereco(Endereco endereco) throws ValidationException{
		enderecoDAO.inserirEndereco(endereco);
	}
	
	public void editarEndereco(Endereco endereco) throws ValidationException{
		enderecoDAO.alterarEndereco(endereco);
	}
	
	public List listarEndereco() {
		return enderecoDAO.listarEndereco();
	}
	
	public void deletarEndereco(Endereco endereco){
		enderecoDAO.deletarEndereco(endereco);
	}
}
