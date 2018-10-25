package br.com.globalgas.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.globalgas.dao.UsuarioDAO;
import br.com.globalgas.model.Usuario;
  
@ManagedBean(name="UsuarioMB")
public class UsuarioManagedBean {

	
      
     private Usuario usuario = new Usuario();
     private UsuarioDAO usuarioDAO = new UsuarioDAO();
      
     public void cadastraUsuario() {
                 
              
                if (usuarioDAO.inserirUsuario(usuario)) {
                     FacesContext.getCurrentInstance().addMessage(
                      null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                      "Sucesso!", "Usuário cadastrado com sucesso!"));
                } else {
                     FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro!", 
                        "Erro no cadastr de usuário!"));
  
                }
                 
                 
                 
         
     }
      
     public Usuario getUsuario() {
          return usuario;
     }
      
     public void setUsuario(Usuario usuario) {
          this.usuario = usuario;
     }



}