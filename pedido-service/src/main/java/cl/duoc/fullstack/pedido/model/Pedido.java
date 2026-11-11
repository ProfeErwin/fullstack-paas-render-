package cl.duoc.fullstack.pedido.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private Long productoId;
    private Integer cantidad;
    private Integer totalEstimado;
    private String estado;
    private LocalDateTime fechaCreacion;
}
