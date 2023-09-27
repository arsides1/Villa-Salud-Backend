package com.villasalud.service.impl;

import com.villasalud.model.Examen;
import com.villasalud.repo.IExamenRepo;
import com.villasalud.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenServiceImpl implements IExamenService{

	@Autowired	
	private IExamenRepo repo;
	
	@Override
	public Examen registrar(Examen obj) {
		return repo.save(obj);		
	}

	@Override
	public Examen modificar(Examen obj) {
		return repo.save(obj);
	}

	@Override
	public List<Examen> listar() { 
		return repo.findAll();
	}

	@Override
	public Examen leerPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

}
