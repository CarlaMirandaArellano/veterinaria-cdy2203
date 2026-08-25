package com.duoc.veterinaria.app.repositorio;

import com.duoc.veterinaria.app.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {}