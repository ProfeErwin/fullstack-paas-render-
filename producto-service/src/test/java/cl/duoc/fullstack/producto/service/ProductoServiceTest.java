package cl.duoc.fullstack.producto.service;

import cl.duoc.fullstack.producto.exception.ResourceNotFoundException;
import cl.duoc.fullstack.producto.model.Producto;
import cl.duoc.fullstack.producto.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {
    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @Test
    void buscarPorId_debeRetornarRegistroCuandoExiste() {
        Producto entidad = new Producto();
        entidad.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entidad));

        Producto resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        verify(repository).findById(1L);
    }

    @Test
    void buscarPorId_debeLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }
}
