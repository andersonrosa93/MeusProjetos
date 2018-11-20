import java.util.Date;

import br.com.framework.dao.UsuarioDAO;
import br.com.framework.model.Usuario;

public class testePersist {

	public static void main(String[] args) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		usuario.setMatricula("20204433");
		usuario.setNomeUsuario("admin");
		usuario.setSenha("123");
		usuario.setUltimoAcesso(new Date());
		
		usuarioDAO.inserirUsuario(usuario);

	}

}
