package com.villasalud.service.impl;

//import com.mitocode.dto.ConsultaListaExamenDTO;

import com.villasalud.dto.ConsultaListaExamenDTO;
import com.villasalud.dto.ConsultaResumenDTO;
import com.villasalud.dto.FiltroConsultaDTO;
import com.villasalud.model.Consulta;
import com.villasalud.model.Examen;
import com.villasalud.repo.IConsultaExamenRepo;
import com.villasalud.repo.IConsultaRepo;
import com.villasalud.service.IConsultaService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService{

	@Autowired	
	private IConsultaRepo repo;

	@Autowired
	private IConsultaExamenRepo ceRepo;
	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO consultaDTO) {
		consultaDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaDTO.getConsulta()));
		repo.save(consultaDTO.getConsulta());
		
		consultaDTO.getListExamen().forEach(ex -> ceRepo.registrar(consultaDTO.getConsulta().getIdConsulta(), ex.getIdExamen()));
		return consultaDTO.getConsulta();

		/*consultaDTO.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(consultaDTO.getConsulta()));
		consultaDTO.getListExamen().forEach(ex -> consultaDTO.getConsulta().addExamen(ex));
		return repo.save(consultaDTO.getConsulta());*/
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return repo.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro) {
		LocalDateTime fechaSgte = filtro.getFechaConsulta().plusDays(1);
		return repo.buscarFecha(filtro.getFechaConsulta(), fechaSgte);
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		// List<Object[]>
		// cantidad fecha
		// [4 , 11/05/2019]
		// [1 , 18/05/2019]
		repo.listarResumen().forEach( x -> {
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}

	@Override
	public Page<Consulta> listares(Pageable pageable) {
		return repo.findAll(pageable);
	}


	@Override
	public Consulta registrar(Consulta obj) {
		obj.getDetalleConsulta().forEach(det -> {
			det.setConsulta(obj);
		});
		
		/*for(DetalleConsulta det : obj.getDetalleConsulta()) {
			det.setConsulta(obj);
		}*/
		return repo.save(obj);		
	}

	@Override
	public Consulta modificar(Consulta obj) {
		return repo.save(obj);
	}

	@Override
	public List<Consulta> listar() {
		return repo.findAll();
	}

	@Override
	public Consulta leerPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	@Override
	public byte[] generarReporte() {
		byte[] data = null;

		//HashMap<String, String> params = new HashMap<String, String>();
		//params.put("txt_empresa", "MitoCode Network");

		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}


}
