package com.villasalud.service.impl;


import com.villasalud.model.Consulta;
import com.villasalud.model.Examen;
import com.villasalud.repo.IConsultaExamenRepo;
import com.villasalud.service.IConsultaExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService {
	
	@Autowired
	private IConsultaExamenRepo repo;
	
	@Override
	public List<Examen> listarExamenesPorConsulta(Integer idconsulta) {
		return repo.listarExamenesPorConsulta(idconsulta);
	}

}
