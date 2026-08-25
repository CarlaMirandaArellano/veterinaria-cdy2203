package com.duoc.veterinaria.app.services;

import com.duoc.veterinaria.app.model.Cita;
import com.duoc.veterinaria.app.model.Paciente;
import com.duoc.veterinaria.app.repositorio.CitaRepositorio;
import com.duoc.veterinaria.app.repositorio.PacienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class veterinariaServices {

    // SE CAMBIAN LAS LISTAS EN MEMORIA POR LOS REPOSITORIOS (BASE DE DATOS)

    private final PacienteRepositorio repoPaciente;
    private final CitaRepositorio repoCita;

    public veterinariaServices(PacienteRepositorio repoPaciente, CitaRepositorio repoCita) {
        this.repoPaciente = repoPaciente;
        this.repoCita = repoCita;
    }

    public Paciente guardaPaciente(Paciente paciente) {
        return repoPaciente.save(paciente);
    }

    public List<Paciente> traerPacientes() {
        return repoPaciente.findAll();
    }

    public Cita guardaCita(Cita cita) {
        return repoCita.save(cita);
    }

    public List<Cita> traerCita() {
        return repoCita.findAll();
    }
}



