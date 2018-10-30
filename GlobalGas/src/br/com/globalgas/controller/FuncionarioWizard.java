package br.com.globalgas.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import br.com.globalgas.dao.FuncionarioDao;
import br.com.globalgas.model.Funcionario;

@ViewScoped
@ManagedBean(name = "FuncionarioWizard")
public class FuncionarioWizard {

	static FuncionarioDao funcDao = FuncionarioDao.getInstance();

	private Funcionario funcionario;
	private boolean skip;

	public FuncionarioWizard() {
		funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void save() {
		try {
			funcDao.persist(funcionario);

		} catch (Exception ex) {
			ex.getStackTrace();
		}
		FacesMessage msg = new FacesMessage("Sucesso", "Bem-vindo(a) :" + funcionario.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		


	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // redefinir caso o usuário volte
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
}
