package com.duoc.veterinaria.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Clase la cual tiene el dato del paciente(mascota) y nombre del dueño.
//Se usa @Entity para convertir la clase en una tabla de la bbdd

@Entity
public class Paciente {

    //Id se define como Identificador único (PK) y el valor se autoincrementa en 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// VARIABLES

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String dueno;


    //CONSTRUCTOR VACIO PARA SPRING
    public Paciente(){}


    //GET Y SET

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie(){
        return especie;
    }

    public void setEspecie(String especie){
        this.especie = especie;
    }

    public String getRaza(){
        return raza;
    }

    public void setRaza(String raza){
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }
}