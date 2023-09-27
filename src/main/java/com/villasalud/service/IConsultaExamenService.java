package com.villasalud.service;



import com.villasalud.model.Consulta;
import com.villasalud.model.Examen;

import java.util.List;

public interface IConsultaExamenService {

	List<Examen> listarExamenesPorConsulta(Integer idconsulta);

}
