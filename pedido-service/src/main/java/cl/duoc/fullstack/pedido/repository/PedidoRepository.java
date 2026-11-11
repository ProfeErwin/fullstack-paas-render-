package cl.duoc.fullstack.pedido.repository;

import cl.duoc.fullstack.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
