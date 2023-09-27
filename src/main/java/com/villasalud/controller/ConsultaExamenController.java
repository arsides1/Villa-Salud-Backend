package com.villasalud.controller;


import com.villasalud.model.Consulta;
import com.villasalud.model.Examen;
import com.villasalud.service.IConsultaExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consultaexamenes")
public class ConsultaExamenController {
	
	@Autowired
	private IConsultaExamenService service;
	
	@GetMapping(value = "/{idConsulta}")
	public ResponseEntity<List<Examen>> listar(@PathVariable("idConsulta") Integer idconsulta) {
		List<Examen> consultasexamen = service.listarExamenesPorConsulta(idconsulta);
		return new ResponseEntity<List<Examen>>(consultasexamen, HttpStatus.OK);
	}

	/*@GetMapping(value = "/{idConsulta}")
	public ResponseEntity<List<Examen>> listarExamenesPorConsulta(@PathVariable("idConsulta") Integer idconsulta) {
		List<Examen> consultasexamen = service.listarExamenesPorConsulta(idconsulta);
		return new ResponseEntity<>(consultasexamen, HttpStatus.OK);
	}*/
}
