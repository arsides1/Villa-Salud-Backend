package com.villasalud.repo;

import com.villasalud.model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface IExamenRepo extends JpaRepository<Examen, Integer>{

	
}
