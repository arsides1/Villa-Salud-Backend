package com.villasalud.repo;

import com.villasalud.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface IMedicoRepo extends JpaRepository<Medico, Integer>{

	
}
