package com.globalgas.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.globalgas.dao.EnderecoDAO;
import com.globalgas.model.Endereco;
import com.globalgas.model.Usuario;

public class EnderecoService {
	
	private EnderecoDAO enderecoDAO = new EnderecoDAO();
	
	public void telaEdicao(Usuario usuario) throws ValidationException {
		if(!usuario.getMatricula().startsWith("ADM")) {
			throw new ValidationException("Somente usuário com perfil ADM podem acessar a página de edição");
		}
	}

	public void salvarEndereco(Endereco endereco) throws ValidationException {
		enderecoDAO.inserirEndereco(endereco);
	}
	
	public List listarEndereco() {
		return enderecoDAO.listarEndereco();
	}

	public void deletarEndereco(Endereco endereco) {
		enderecoDAO.deletarEndereco(endereco);
	}
	

}
