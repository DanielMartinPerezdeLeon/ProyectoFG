package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//CRUD
	
	/*List<Usuario> getAll();
	Usuario getByIdentificacion(String identificacion);
	
	Boolean createUsuario(Usuario usu);
	
	Boolean replaceUsuario(String identificacion, Usuario nuevo);
	
	Boolean deleteUsuario(String identificación);*/

}
