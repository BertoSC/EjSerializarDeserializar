package org.example;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

    // con esta etiqueta podemos indicarle a JsonB el orden que queramos en los parámetros de los objetos
    //@JsonbPropertyOrder({"materia", "fecha", "participantes"})
    public class Examen implements Serializable {
        private String materia;
        private LocalDateTime fecha;
        private List<String> participantes;

        // es importante tener constructor por defecto, getters y setters de los datos y toString()
        public Examen(){

        }

        public Examen(String materia, LocalDateTime fecha, List<String> participantes) {
            this.materia = materia;
            this.fecha = fecha;
            this.participantes = participantes;
        }

        public String getMateria() {
            return materia;
        }

        public LocalDateTime getFecha() {
            return fecha;
        }

        public List<String> getParticipantes() {
            return participantes;
        }

        public void setMateria(String materia) {
            this.materia = materia;
        }

        public void setFecha(LocalDateTime fecha) {
            this.fecha = fecha;
        }

        public void setParticipantes(List<String> participantes) {
            this.participantes = participantes;
        }

        @Override
        public String toString() {
            //DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //co la clase DateTimeFormatter podemos establecer un formato de fecha con un patrón
            // aquí le digo "ponme el nombre del día, el día numérico el més en letra y el año en letra, con un separador y letras por el medio
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy - HH:mm");
            //aquí creo un string aplicándole el formato especificado a la fecha mediante fomrat()
            String fechaFormat = fecha.format(formato);
            StringBuilder sb = new StringBuilder();
            sb.append("Examen de "+materia).append(System.lineSeparator());
            sb.append(fechaFormat).append(System.lineSeparator());
            sb.append("Lista de participantes:").append(System.lineSeparator());
            for (String s: participantes) {
                sb.append(s).append(System.lineSeparator());
            }
            return sb.toString();
        }

}
