package com.villasalud.service.impl;


import com.villasalud.model.Usuario;
import com.villasalud.repo.ILoginRepo;
import com.villasalud.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private ILoginRepo repo;

	@Override
	public int cambiarClave(String clave, String nombre) {
		int rpta = 0;
		try {
			repo.cambiarClave(clave, nombre);
			rpta = 1;
		} catch (Exception e) {
			rpta = 0;
		}
		return rpta;
	}
	
	@Override
	public Usuario verificarNombreUsuario(String usuario) throws Exception {
		Usuario us = null;
		try {
			us = repo.verificarNombreUsuario(usuario);
			us = us != null ? us : new Usuario();
		} catch (Exception e) {
			us = new Usuario();
		}
		return us;
	}


}
