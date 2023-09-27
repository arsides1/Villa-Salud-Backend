package com.villasalud.service.impl;

import com.villasalud.model.Especialidad;
import com.villasalud.repo.IEspecialidadRepo;
import com.villasalud.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired	
	private IEspecialidadRepo repo;
	
	@Override
	public Especialidad registrar(Especialidad obj) {
		return repo.save(obj);		
	}

	@Override
	public Especialidad modificar(Especialidad obj) {

		return repo.save(obj);
	}

	@Override
	public List<Especialidad> listar() { 
		return repo.findAll();
	}

	@Override
	public Especialidad leerPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

}
