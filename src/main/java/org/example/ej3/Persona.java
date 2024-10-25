package org.example.ej3;
import org.example.Direccion;

import java.util.List;

public class Persona {
    String nombre;
    String edad;
    Direccion direccion;
    List<Persona> amigos;

    public Persona(String n, String e, Direccion d) {
        this.nombre = n;
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

    public List<Persona> getLista() {
        return amigos;
    }

    public void setLista(List<Persona> lista) {
        this.amigos = lista;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", direccion=" + direccion +
                ", lista=" + amigos +
                '}';
    }
}


