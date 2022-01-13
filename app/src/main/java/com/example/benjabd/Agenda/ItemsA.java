package com.example.benjabd.Agenda;

import android.widget.ImageView;

public class ItemsA {

    private String Nombre, Apellido, Telefono,FechaI,FechaF;
    ImageView imCurso;

    public ItemsA(String nombre, String apellido, String telefono, String fechaI, String fechaF) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Telefono = telefono;
        this.FechaI = fechaI;
        this.FechaF = fechaF;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFechaI() {
        return FechaI;
    }

    public void setFechaI(String fechaI) {
        FechaI = fechaI;
    }

    public String getFechaF() {
        return FechaF;
    }

    public void setFechaF(String fechaF) {
        FechaF = fechaF;
    }
}
