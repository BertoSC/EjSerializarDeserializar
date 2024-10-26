package org.example.ej5;
import org.example.Direccion;
import java.util.List;

public class Persona5 {
        String nombre;
        String edad;
        Direccion direccion;
        List<Persona5> amigos;
        String[] hobbies;

        public Persona5(String n, String e, Direccion d) {
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

        public List<Persona5> getLista() {
            return amigos;
        }

        public void setLista(List<Persona5> lista) {
            this.amigos = lista;
        }

        public String[] getHobbies() {
            return hobbies;
         }

          public void setHobbies(String[] hobbies) {
                this.hobbies = hobbies;
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
