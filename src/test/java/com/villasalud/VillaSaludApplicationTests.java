package com.villasalud;

import com.villasalud.model.Usuario;
import com.villasalud.repo.IUsuarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VillaSaludApplicationTests {

	@Autowired
	private IUsuarioRepo repo;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Test
	public void crearlJsuariO() {
		Usuario us = new Usuario();
		us.setIdUsuario(3);
		us.setUsername("usuarioBD");
		us.setPassword(bcrypt.encode("123"));
		us.setEnabled(true);
		Usuario retorno = repo.save(us);
		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));


	}
}
