package com.duoc.veterinaria.app.repositorio;

import com.duoc.veterinaria.app.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//REPOSITORIO PARA CONECTAR SQL CON LA BBDD DE PACIENTE.

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {}