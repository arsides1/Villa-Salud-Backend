package com.villasalud.service.impl;

import com.villasalud.model.Medico;
import com.villasalud.repo.IMedicoRepo;
import com.villasalud.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoServiceImpl implements IMedicoService{

	@Autowired	
	private IMedicoRepo repo;
	
	@Override
	public Medico registrar(Medico obj) {
		return repo.save(obj);		
	}

	@Override
	public Medico modificar(Medico obj) {
		return repo.save(obj);
	}

	@PreAuthorize("@restAuthServiceImpl.hasAccess('listar')")
	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@Override
	public List<Medico> listar() { 
		return repo.findAll();
	}

	@Override
	public Medico leerPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

}
