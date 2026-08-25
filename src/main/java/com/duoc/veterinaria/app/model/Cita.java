package com.duoc.veterinaria.app.model;

// CLASE QUE CONTIENE LA INFORMACIÓN DE LA CITA COMO FECHA, HORA, EL MOTIVO Y EL NOMBRE DE VETERINARIO QUE HARA LA CONSULTA.
//Se usa @Entity para convertir la clase en una tabla de la bbdd

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // VARIABLES
    private String nombrePaciente;
    private String fecha;
    private String hora;
    private String motivo;
    private String veterinario;

    // CONSTRUCTOR VACIO PARA SPRING
    public Cita() {}

    // GET Y SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }
}