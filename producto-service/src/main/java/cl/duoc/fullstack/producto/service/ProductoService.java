package cl.duoc.fullstack.producto.service;

import cl.duoc.fullstack.producto.dto.ProductoRequest;
import cl.duoc.fullstack.producto.exception.ResourceNotFoundException;
import cl.duoc.fullstack.producto.model.Producto;
import cl.duoc.fullstack.producto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repository;

    public List<Producto> listar() {
        log.info("Listando productos");
        return repository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe producto con ID " + id));
    }

    public Producto crear(ProductoRequest request) {
        Producto entidad = new Producto();
        entidad.setNombre(request.getNombre());
        entidad.setCategoria(request.getCategoria());
        entidad.setStock(request.getStock());
        entidad.setPrecio(request.getPrecio());
        log.info("Creando producto: {}", request);
        return repository.save(entidad);
    }

    public Producto actualizar(Long id, ProductoRequest request) {
        Producto entidad = buscarPorId(id);
        entidad.setNombre(request.getNombre());
        entidad.setCategoria(request.getCategoria());
        entidad.setStock(request.getStock());
        entidad.setPrecio(request.getPrecio());
        log.info("Actualizando producto ID {}", id);
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        Producto entidad = buscarPorId(id);
        repository.delete(entidad);
        log.info("Eliminado producto ID {}", id);
    }
}
