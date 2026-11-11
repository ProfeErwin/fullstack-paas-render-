package cl.duoc.fullstack.pedido.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    @NotNull(message = "El cliente es obligatorio") private Long clienteId;
    @NotNull(message = "El producto es obligatorio") private Long productoId;
    @Min(value = 1, message = "La cantidad debe ser mayor a cero") private Integer cantidad;
}
