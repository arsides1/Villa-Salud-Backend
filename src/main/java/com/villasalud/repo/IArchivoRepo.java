package com.villasalud.repo;


import com.villasalud.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArchivoRepo extends JpaRepository<Archivo, Integer>{

}
