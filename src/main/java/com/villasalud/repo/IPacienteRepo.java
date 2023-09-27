package com.villasalud.repo;

import com.villasalud.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface IPacienteRepo extends JpaRepository<Paciente, Integer>{

	
}
