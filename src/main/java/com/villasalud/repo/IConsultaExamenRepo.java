package com.villasalud.repo;


import com.villasalud.model.Consulta;
import com.villasalud.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IConsultaExamenRepo extends JpaRepository<Consulta, Integer>{

	//@Transactional
	@Modifying
	@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);
	
	/*@Query("from Consulta ce where ce.idConsulta = :idConsulta")
	List<Consulta> listarExamenesPorConsulta(@Param("idConsulta") Integer idconsulta);*/
	@Query("select ce.examenes from Consulta ce where ce.idConsulta = :idConsulta")
	List<Examen> listarExamenesPorConsulta(@Param("idConsulta") Integer idconsulta);
}
