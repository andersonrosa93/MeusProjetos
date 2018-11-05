package main;

import java.util.Date;

import com.globalgas.dao.UsuarioDAO;
import com.globalgas.model.Usuario;

public class Testar {

	public static void main(String[] args) {
		UsuarioDAO dao = new UsuarioDAO();
		
		Usuario usuario = new Usuario();
//		usuario.setId(7);
		usuario.setNomeUsuario("---teste 2");
		usuario.setSenha("---234");
		usuario.setMatricula("-----");
		usuario.setUltimoAcesso(new Date());
		
//		dao.inserirUsuario(usuario);
		
		
		for (Object objeto : dao.listarUsuario()) {
			Usuario usuario2 = (Usuario) objeto;
			System.out.println(usuario2.getMatricula());
			
		}// dao.listarUsuario();

	}

}
