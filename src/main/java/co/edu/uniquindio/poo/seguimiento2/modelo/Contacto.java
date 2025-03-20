package co.edu.uniquindio.poo.seguimiento2.modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

    @Data
    @AllArgsConstructor
    public class Contacto{
        public String nombre;
        public String apellido;
        public String telefono;
        public String email;
        public String direccion;
        public LocalDate fechaNacimiento;
    }





