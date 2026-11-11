package cl.duoc.fullstack.producto.controller;

import cl.duoc.fullstack.producto.dto.ProductoRequest;
import cl.duoc.fullstack.producto.model.Producto;
import cl.duoc.fullstack.producto.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService service;

    @GetMapping("/")
    public CollectionModel<EntityModel<Producto>> listar() {
        List<EntityModel<Producto>> recursos = service.listar().stream().map(this::toModel).toList();
        return CollectionModel.of(recursos, linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Producto> buscar(@PathVariable Long id) {
        return toModel(service.buscarPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<Producto>> crear(@Valid @RequestBody ProductoRequest request) {
        Producto creado = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(creado));
    }


    @PutMapping("/{id}")
    public EntityModel<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return toModel(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<Producto> toModel(Producto entidad) {
        return EntityModel.of(entidad,
                linkTo(methodOn(ProductoController.class).buscar(entidad.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).listar()).withRel("todos"));
    }
}
