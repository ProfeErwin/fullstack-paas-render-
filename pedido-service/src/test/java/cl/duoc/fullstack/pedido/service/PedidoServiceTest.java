package cl.duoc.fullstack.pedido.service;

import cl.duoc.fullstack.pedido.exception.ResourceNotFoundException;
import cl.duoc.fullstack.pedido.model.Pedido;
import cl.duoc.fullstack.pedido.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {
    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoService service;

    @Test
    void buscarPorId_debeRetornarRegistroCuandoExiste() {
        Pedido entidad = new Pedido();
        entidad.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entidad));

        Pedido resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        verify(repository).findById(1L);
    }

    @Test
    void buscarPorId_debeLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }
}
