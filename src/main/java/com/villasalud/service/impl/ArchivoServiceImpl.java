package com.villasalud.service.impl;


import com.villasalud.model.Archivo;
import com.villasalud.repo.IArchivoRepo;
import com.villasalud.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArchivoServiceImpl implements IArchivoService {

	@Autowired
	private IArchivoRepo repo;

	@Override
	public int guardar(Archivo archivo) {
		Archivo ar = repo.save(archivo);
		return ar.getIdArchivo() > 0 ? 1 : 0;
	}

	@Override
	public byte[] leerArchivo(Integer idArchivo) {
		Optional<Archivo> archivo = repo.findById(idArchivo);
		return archivo.map(Archivo::getValue).orElse(null);
	}
}
