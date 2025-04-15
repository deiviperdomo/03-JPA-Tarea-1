package es.eviden.club;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.eviden.club.entities.Barco;
import es.eviden.club.modelo.rpository.BarcoRepository;
import es.eviden.club.modelo.service.BarcoServiceImpl;

@ExtendWith(MockitoExtension.class)
class BarcoServiceImplTest {

    @Mock
    private BarcoRepository barcoRepo;
    
    @InjectMocks
    private BarcoServiceImpl barcoService;
    
    private Barco barco;
    private List<Barco> barcoList;
    
    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        barco = new Barco();
        barco.setId(1L);
        // Configurar otros atributos necesarios de Barco
        
        barcoList = new ArrayList<>();
        barcoList.add(barco);
    }
    
    @Test
    void testAlta_BarcoNoExiste() {
        // Configuración
        when(barcoRepo.existsById(barco.getId())).thenReturn(false);
        when(barcoRepo.save(barco)).thenReturn(barco);
        
        // Ejecución
        Barco resultado = barcoService.alta(barco);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(barco, resultado);
        verify(barcoRepo).existsById(barco.getId());
        verify(barcoRepo).save(barco);
    }
    
    @Test
    void testAlta_BarcoYaExiste() {
        // Configuración
        when(barcoRepo.existsById(barco.getId())).thenReturn(true);
        
        // Ejecución
        Barco resultado = barcoService.alta(barco);
        
        // Verificación
        assertNull(resultado);
        verify(barcoRepo).existsById(barco.getId());
        verify(barcoRepo, never()).save(any());
    }
    
    @Test
    void testModificar_BarcoExiste() {
        // Configuración
        when(barcoRepo.existsById(barco.getId())).thenReturn(true);
        when(barcoRepo.save(barco)).thenReturn(barco);
        
        // Ejecución
        Barco resultado = barcoService.modificar(barco);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(barco, resultado);
        verify(barcoRepo).existsById(barco.getId());
        verify(barcoRepo).save(barco);
    }
    
    @Test
    void testModificar_BarcoNoExiste() {
        // Configuración
        when(barcoRepo.existsById(barco.getId())).thenReturn(false);
        
        // Ejecución
        Barco resultado = barcoService.modificar(barco);
        
        // Verificación
        assertNull(resultado);
        verify(barcoRepo).existsById(barco.getId());
        verify(barcoRepo, never()).save(any());
    }
    
    @Test
    void testModificar_ConExcepcion() {
        // Configuración
        when(barcoRepo.existsById(barco.getId())).thenReturn(true);
        when(barcoRepo.save(barco)).thenThrow(new RuntimeException("Error simulado"));
        
        // Ejecución
        Barco resultado = barcoService.modificar(barco);
        
        // Verificación
        assertNull(resultado);
        verify(barcoRepo).existsById(barco.getId());
        verify(barcoRepo).save(barco);
    }
    
    @Test
    void testEliminar() {
        // Configuración
        when(barcoRepo.existsById(barco.getId())).thenReturn(true);
        doNothing().when(barcoRepo).deleteById(barco.getId());
        
        // Ejecución
        int resultado = barcoService.eliminar(barco);
        
        // Verificación
        assertEquals(1, resultado);
        verify(barcoRepo).existsById(barco.getId());
        verify(barcoRepo).deleteById(barco.getId());
    }
    
    @Test
    void testEliminarPorId_BarcoExiste() {
        // Configuración
        Long id = 1L;
        when(barcoRepo.existsById(id)).thenReturn(true);
        doNothing().when(barcoRepo).deleteById(id);
        
        // Ejecución
        int resultado = barcoService.eliminarPorId(id);
        
        // Verificación
        assertEquals(1, resultado);
        verify(barcoRepo).existsById(id);
        verify(barcoRepo).deleteById(id);
    }
    
    @Test
    void testEliminarPorId_BarcoNoExiste() {
        // Configuración
        Long id = 1L;
        when(barcoRepo.existsById(id)).thenReturn(false);
        
        // Ejecución
        int resultado = barcoService.eliminarPorId(id);
        
        // Verificación
        assertEquals(0, resultado);
        verify(barcoRepo).existsById(id);
        verify(barcoRepo, never()).deleteById(any());
    }
    
    @Test
    void testEliminarPorId_ConExcepcion() {
        // Configuración
        Long id = 1L;
        when(barcoRepo.existsById(id)).thenReturn(true);
        doThrow(new RuntimeException("Error simulado")).when(barcoRepo).deleteById(id);
        
        // Ejecución
        int resultado = barcoService.eliminarPorId(id);
        
        // Verificación
        assertEquals(-1, resultado);
        verify(barcoRepo).existsById(id);
        verify(barcoRepo).deleteById(id);
    }
    
    @Test
    void testBuscarPorId_BarcoExiste() {
        // Configuración
        Long id = 1L;
        when(barcoRepo.findById(id)).thenReturn(Optional.of(barco));
        
        // Ejecución
        Barco resultado = barcoService.buscarPorId(id);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(barco, resultado);
        verify(barcoRepo).findById(id);
    }
    
    @Test
    void testBuscarPorId_BarcoNoExiste() {
        // Configuración
        Long id = 1L;
        when(barcoRepo.findById(id)).thenReturn(Optional.empty());
        
        // Ejecución
        Barco resultado = barcoService.buscarPorId(id);
        
        // Verificación
        assertNull(resultado);
        verify(barcoRepo).findById(id);
    }
    
    @Test
    void testBuscarTodos() {
        // Configuración
        when(barcoRepo.findAll()).thenReturn(barcoList);
        
        // Ejecución
        List<Barco> resultado = barcoService.buscarTodos();
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(barcoList, resultado);
        verify(barcoRepo).findAll();
    }
}