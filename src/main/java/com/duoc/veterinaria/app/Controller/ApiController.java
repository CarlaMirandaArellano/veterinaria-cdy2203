package com.duoc.veterinaria.app.Controller;

import com.duoc.veterinaria.app.model.Cita;
import com.duoc.veterinaria.app.model.Paciente;
import com.duoc.veterinaria.app.services.veterinariaServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final veterinariaServices servicio;

    public ApiController(veterinariaServices servicio) {
        this.servicio = servicio;
    }

    // GESTION DE PACIENTES (JSON, protegido por JWT)
    @GetMapping("/pacientes")
    public List<Paciente> listarPacientes() {
        return servicio.traerPacientes();
    }

    @PostMapping("/pacientes")
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return servicio.guardaPaciente(paciente);
    }

    // PROGRAMACION DE CITAS (JSON, protegido por JWT)
    @GetMapping("/citas")
    public List<Cita> listarCitas() {
        return servicio.traerCita();
    }

    @PostMapping("/citas")
    public Cita guardarCita(@RequestBody Cita cita) {
        return servicio.guardaCita(cita);
    }
}