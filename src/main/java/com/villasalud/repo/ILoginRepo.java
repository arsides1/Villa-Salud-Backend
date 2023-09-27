package com.villasalud.repo;

import com.villasalud.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ILoginRepo extends JpaRepository<Usuario, Integer> {
	/**
	 * Busca un usuario por su nombre de usuario.
	 * @param usuario Nombre de usuario a verificar.
	 * @return Usuario con el nombre de usuario especificado.
	 * @throws Exception Si ocurre un error durante la consulta.
	 */
	@Query("FROM Usuario us where us.username = :usuario")
	Usuario verificarNombreUsuario(@Param("usuario") String usuario) throws Exception;
	//Usuario findByUsername(String username);

	/**
	 * Cambia la contraseña de un usuario especificado por su nombre de usuario.
	 * @param clave Nueva contraseña.
	 * @param nombre Nombre de usuario al que se le cambiará la contraseña.
	 * @throws Exception Si ocurre un error durante la actualización.
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Usuario us SET us.password = :clave WHERE us.username = :nombre")
	void cambiarClave(@Param("clave") String clave, @Param("nombre") String nombre) throws Exception;
	
}
