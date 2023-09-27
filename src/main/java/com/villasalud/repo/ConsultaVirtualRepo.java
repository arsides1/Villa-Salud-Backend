package com.villasalud.repo;

import com.villasalud.model.ConsultaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaVirtualRepo extends JpaRepository<ConsultaVirtual, Long> {
}
