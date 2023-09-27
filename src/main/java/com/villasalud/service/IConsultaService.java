package com.villasalud.service;

//import com.mitocode.dto.ConsultaListaExamenDTO;
import com.villasalud.dto.ConsultaListaExamenDTO;
import com.villasalud.dto.ConsultaResumenDTO;
import com.villasalud.dto.FiltroConsultaDTO;
import com.villasalud.model.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IConsultaService extends ICRUD<Consulta>{

	Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO);

	//List<Consulta> getConsultaListporFechas(String dni, String nombreCompleto,Date fechaInicio, Date fechaFin);

	List<Consulta> buscar(FiltroConsultaDTO filtro);

	List<Consulta> buscarFecha(FiltroConsultaDTO filtro);

	List<ConsultaResumenDTO> listarResumen();
	Page<Consulta> listares(Pageable pageable);
	byte[] generarReporte();
}
