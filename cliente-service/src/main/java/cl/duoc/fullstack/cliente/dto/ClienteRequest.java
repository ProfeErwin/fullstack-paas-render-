package cl.duoc.fullstack.cliente.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    @NotBlank(message = "El RUT es obligatorio") private String rut;
    @NotBlank(message = "El nombre es obligatorio") private String nombre;
    @Email(message = "El email debe ser válido") @NotBlank(message = "El email es obligatorio") private String email;
    @NotBlank(message = "El teléfono es obligatorio") private String telefono;
}
