package cl.duoc.fullstack.producto.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {
    @NotBlank(message = "El nombre es obligatorio") private String nombre;
    @NotBlank(message = "La categoría es obligatoria") private String categoria;
    @Min(value = 0, message = "El stock no puede ser negativo") private Integer stock;
    @Min(value = 1, message = "El precio debe ser mayor a cero") private Integer precio;
}
