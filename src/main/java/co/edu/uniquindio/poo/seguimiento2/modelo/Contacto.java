package co.edu.uniquindio.poo.seguimiento2.modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

    @Data
    @RequiredArgsConstructor
    public class Contacto {
        @NonNull
        private String nombre;
        @NonNull private String apellido;
        @NonNull private String telefono;
        @NonNull private String email;
        @NonNull private String direccion;
        @NonNull private LocalDate fechaNacimiento;
        private String fotoPerfil;
}





