package com.duoc.veterinaria.app.Controller;

import com.duoc.veterinaria.app.model.Cita;
import com.duoc.veterinaria.app.model.Paciente;
import com.duoc.veterinaria.app.services.veterinariaServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    // INYECCION DE DEPENDENCIAS DEL SERVICIO
    private final veterinariaServices servicio;

    public AppController(veterinariaServices servicio) {
        this.servicio = servicio;
    }


    // RUTAS PUBLICAS

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    // RUTAS PRIVADAS

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    // GESTION DE PACIENTES
    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", servicio.traerPacientes());
        model.addAttribute("nuevoPaciente", new Paciente());
        return "pacientes";
    }

    @PostMapping("/pacientes/guardar")
    public String guardarPaciente(@ModelAttribute Paciente paciente) {
        servicio.guardaPaciente(paciente);
        return "redirect:/pacientes";
    }

    // PROGRAMACION DE CITAS
    @GetMapping("/citas")
    public String listarCitas(Model model) {
        model.addAttribute("citas", servicio.traerCita());
        model.addAttribute("nuevaCita", new Cita());
        return "citas";
    }

    @PostMapping("/citas/guardar")
    public String guardarCita(@ModelAttribute Cita cita) {
        servicio.guardaCita(cita);
        return "redirect:/citas";
    }
}