package org.example.ej2;

import org.example.Direccion;

public class Persona {
    String nombre;
    String edad;
    Direccion direccion;

    public Persona (String n, String e, Direccion d){
        this.nombre =n;
        this.edad = e;
        this.direccion = d;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", direccion=" + direccion +
                '}';
    }


}
