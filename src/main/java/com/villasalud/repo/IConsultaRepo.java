package com.villasalud.repo;

import com.villasalud.model.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface IConsultaRepo extends JpaRepository<Consulta, Integer> {

    //la consulta es que va buscar la tabla consulta en paciente, dentro de paciente va buscar su nombre o apellidos
    @Query("from Consulta c where c.paciente.dni =:dni or LOWER(c.paciente.nombres) like %:nombreCompleto% or LOWER(c.paciente.apellidos) like %:nombreCompleto%")
    List<Consulta> buscar(@Param("dni")String dni,@Param("nombreCompleto") String nombreCompleto);
    @Query("from Consulta c where c.fecha between :fechaConsulta and :fechaSgte")
    List<Consulta> buscarFecha(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaSgte") LocalDateTime fechaSgte);



    /*@Query(" select c from Consulta c where c.paciente.dni like %?1% and c.fecha between ?2 and ?3")
    List<Consulta> getConsultaByDniAndFechaBetween(String dni, Date fechaInicio, Date fechaFin);

    @Query(" select c from Consulta c where LOWER(c.paciente.nombres) like %?1% or LOWER(c.paciente.apellidos) like %?2% and c.fecha between ?3 and ?4")
    List<Consulta> getConsultaByPacienteAndFechaBetween( String nombreCompleto,Date fechaInicio, Date fechaFin);
    // >= <
    @Query("select c from Consulta c where c.fecha between ?1 and ?2")
    List<Consulta> getConsultaByFechaBetween(Date fechaInicio, Date fechaFin);*/

    @Query(value = "CALL fn_listarResumen()", nativeQuery = true)
    List<Object[]> listarResumen();
}
