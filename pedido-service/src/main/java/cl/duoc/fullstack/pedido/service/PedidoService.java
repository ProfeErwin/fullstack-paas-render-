package cl.duoc.fullstack.pedido.service;

import cl.duoc.fullstack.pedido.dto.*;
import cl.duoc.fullstack.pedido.exception.ResourceNotFoundException;
import cl.duoc.fullstack.pedido.model.Pedido;
import cl.duoc.fullstack.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repository;
    private final WebClient.Builder webClientBuilder;

    @Value("${services.cliente.url:http://CLIENTE-SERVICE}")
    private String clienteServiceUrl;

    @Value("${services.producto.url:http://PRODUCTO-SERVICE}")
    private String productoServiceUrl;

    public List<Pedido> listar() { return repository.findAll(); }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe pedido con ID " + id));
    }

    public Pedido crear(PedidoRequest request) {
        ClienteResponse cliente = webClientBuilder.build().get()
                .uri(clienteServiceUrl + "/api/clientes/{id}", request.getClienteId())
                .retrieve().bodyToMono(ClienteResponse.class).block();

        ProductoResponse producto = webClientBuilder.build().get()
                .uri(productoServiceUrl + "/api/productos/{id}", request.getProductoId())
                .retrieve().bodyToMono(ProductoResponse.class).block();

        int total = producto.precio() * request.getCantidad();
        Pedido pedido = Pedido.builder()
                .clienteId(cliente.id())
                .productoId(producto.id())
                .cantidad(request.getCantidad())
                .totalEstimado(total)
                .estado("CREADO")
                .fechaCreacion(LocalDateTime.now())
                .build();
        log.info("Pedido creado para cliente {} y producto {}", cliente.nombre(), producto.nombre());
        return repository.save(pedido);
    }

    public void eliminar(Long id) { repository.delete(buscarPorId(id)); }
}
