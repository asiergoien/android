package com.example.e17recyclerview;

import java.util.Date;

public class Contacto {
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    static int iKont;

    // Este constructor es solo para poder llenar la lista con datos.
    public Contacto() {
        this.nombre = "Asier" + iKont++;
        this.apellido = "Goienetxea";
        this.fechaNacimiento = new Date();
    }

    public Contacto(String pNombre, String apellido ,Date pFechaNacimiento) {
        this.nombre = pNombre;
        this.fechaNacimiento = pFechaNacimiento;
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
