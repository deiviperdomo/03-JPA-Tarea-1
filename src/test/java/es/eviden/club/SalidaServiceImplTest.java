package es.eviden.club;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.eviden.club.entities.Salida;
import es.eviden.club.modelo.rpository.SalidaRpository;
import es.eviden.club.modelo.service.SalidaServiceImpl;

@ExtendWith(MockitoExtension.class)
class SalidaServiceImplTest {

    @Mock
    private SalidaRpository salidaRepo;
    
    @InjectMocks
    private SalidaServiceImpl salidaService;
    
    private Salida salida;
    private List<Salida> salidaList;
    
    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        salida = new Salida();
        salida.setId(1L);
        // Configurar otros atributos necesarios de Salida
        
        salidaList = new ArrayList<>();
        salidaList.add(salida);
    }
    
    @Test
    void testAlta_SalidaNoExiste() {
        // Configuración
        when(salidaRepo.existsById(salida.getId())).thenReturn(false);
        when(salidaRepo.save(salida)).thenReturn(salida);
        
        // Ejecución
        Salida resultado = salidaService.alta(salida);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(salida, resultado);
        verify(salidaRepo).existsById(salida.getId());
        verify(salidaRepo).save(salida);
    }
    
    @Test
    void testAlta_SalidaYaExiste() {
        // Configuración
        when(salidaRepo.existsById(salida.getId())).thenReturn(true);
        
        // Ejecución
        Salida resultado = salidaService.alta(salida);
        
        // Verificación
        assertNull(resultado);
        verify(salidaRepo).existsById(salida.getId());
        verify(salidaRepo, never()).save(any());
    }
    
    @Test
    void testModificar_SalidaExiste() {
        // Configuración
        when(salidaRepo.existsById(salida.getId())).thenReturn(true);
        when(salidaRepo.save(salida)).thenReturn(salida);
        
        // Ejecución
        Salida resultado = salidaService.modificar(salida);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(salida, resultado);
        verify(salidaRepo).existsById(salida.getId());
        verify(salidaRepo).save(salida);
    }
    
    @Test
    void testModificar_SalidaNoExiste() {
        // Configuración
        when(salidaRepo.existsById(salida.getId())).thenReturn(false);
        
        // Ejecución
        Salida resultado = salidaService.modificar(salida);
        
        // Verificación
        assertNull(resultado);
        verify(salidaRepo).existsById(salida.getId());
        verify(salidaRepo, never()).save(any());
    }
    
    @Test
    void testModificar_ConExcepcion() {
        // Configuración
        when(salidaRepo.existsById(salida.getId())).thenReturn(true);
        when(salidaRepo.save(salida)).thenThrow(new RuntimeException("Error simulado"));
        
        // Ejecución
        Salida resultado = salidaService.modificar(salida);
        
        // Verificación
        assertNull(resultado);
        verify(salidaRepo).existsById(salida.getId());
        verify(salidaRepo).save(salida);
    }
    
    @Test
    void testEliminar() {
        // Configuración
        when(salidaRepo.existsById(salida.getId())).thenReturn(true);
        doNothing().when(salidaRepo).deleteById(salida.getId());
        
        // Ejecución
        int resultado = salidaService.eliminar(salida);
        
        // Verificación
        assertEquals(1, resultado);
        verify(salidaRepo).existsById(salida.getId());
        verify(salidaRepo).deleteById(salida.getId());
    }
    
    @Test
    void testEliminarPorId_SalidaExiste() {
        // Configuración
        Long id = 1L;
        when(salidaRepo.existsById(id)).thenReturn(true);
        doNothing().when(salidaRepo).deleteById(id);
        
        // Ejecución
        int resultado = salidaService.eliminarPorId(id);
        
        // Verificación
        assertEquals(1, resultado);
        verify(salidaRepo).existsById(id);
        verify(salidaRepo).deleteById(id);
    }
    
    @Test
    void testEliminarPorId_SalidaNoExiste() {
        // Configuración
        Long id = 1L;
        when(salidaRepo.existsById(id)).thenReturn(false);
        
        // Ejecución
        int resultado = salidaService.eliminarPorId(id);
        
        // Verificación
        assertEquals(0, resultado);
        verify(salidaRepo).existsById(id);
        verify(salidaRepo, never()).deleteById(any());
    }
    
    @Test
    void testEliminarPorId_ConExcepcion() {
        // Configuración
        Long id = 1L;
        when(salidaRepo.existsById(id)).thenReturn(true);
        doThrow(new RuntimeException("Error simulado")).when(salidaRepo).deleteById(id);
        
        // Ejecución
        int resultado = salidaService.eliminarPorId(id);
        
        // Verificación
        assertEquals(-1, resultado);
        verify(salidaRepo).existsById(id);
        verify(salidaRepo).deleteById(id);
    }
    
    @Test
    void testBuscarPorId_SalidaExiste() {
        // Configuración
        Long id = 1L;
        when(salidaRepo.findById(id)).thenReturn(Optional.of(salida));
        
        // Ejecución
        Salida resultado = salidaService.buscarPorId(id);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(salida, resultado);
        verify(salidaRepo).findById(id);
    }
    
    @Test
    void testBuscarPorId_SalidaNoExiste() {
        // Configuración
        Long id = 1L;
        when(salidaRepo.findById(id)).thenReturn(Optional.empty());
        
        // Ejecución
        Salida resultado = salidaService.buscarPorId(id);
        
        // Verificación
        assertNull(resultado);
        verify(salidaRepo).findById(id);
    }
    
    @Test
    void testBuscarTodos() {
        // Configuración
        when(salidaRepo.findAll()).thenReturn(salidaList);
        
        // Ejecución
        List<Salida> resultado = salidaService.buscarTodos();
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(salidaList, resultado);
        verify(salidaRepo).findAll();
    }
}