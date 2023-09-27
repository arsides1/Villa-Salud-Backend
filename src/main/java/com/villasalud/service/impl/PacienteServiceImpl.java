package com.villasalud.service.impl;

import com.villasalud.model.Paciente;
import com.villasalud.repo.IPacienteRepo;
import com.villasalud.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService{

	@Autowired	
	private IPacienteRepo repo;
	
	@Override
	public Paciente registrar(Paciente pac) {
		return repo.save(pac);
		
	}

	@Override
	public Paciente modificar(Paciente pac) {
		return repo.save(pac);
	}

	@Override
	public List<Paciente> listar() { 
		return repo.findAll();
	}

	@Override
	public Paciente leerPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Page<Paciente> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
}
