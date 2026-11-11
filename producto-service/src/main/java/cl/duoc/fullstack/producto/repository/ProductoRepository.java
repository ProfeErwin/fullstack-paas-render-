package cl.duoc.fullstack.producto.repository;

import cl.duoc.fullstack.producto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
