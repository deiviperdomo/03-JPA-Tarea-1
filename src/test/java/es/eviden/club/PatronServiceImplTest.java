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

import es.eviden.club.entities.Patron;
import es.eviden.club.modelo.rpository.PatronRepository;
import es.eviden.club.modelo.service.PatronServiceImpl;

@ExtendWith(MockitoExtension.class)
class PatronServiceImplTest {

    @Mock
    private PatronRepository patronRepo;
    
    @InjectMocks
    private PatronServiceImpl patronService;
    
    private Patron patron;
    private List<Patron> patronList;
    
    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        patron = new Patron();
        patron.setId(1L);
        // Configurar otros atributos necesarios de Patron
        
        patronList = new ArrayList<>();
        patronList.add(patron);
    }
    
    @Test
    void testAlta_PatronNoExiste() {
        // Configuración
        when(patronRepo.existsById(patron.getId())).thenReturn(false);
        when(patronRepo.save(patron)).thenReturn(patron);
        
        // Ejecución
        Patron resultado = patronService.alta(patron);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(patron, resultado);
        verify(patronRepo).existsById(patron.getId());
        verify(patronRepo).save(patron);
    }
    
    @Test
    void testAlta_PatronYaExiste() {
        // Configuración
        when(patronRepo.existsById(patron.getId())).thenReturn(true);
        
        // Ejecución
        Patron resultado = patronService.alta(patron);
        
        // Verificación
        assertNull(resultado);
        verify(patronRepo).existsById(patron.getId());
        verify(patronRepo, never()).save(any());
    }
    
    @Test
    void testModificar_PatronExiste() {
        // Configuración
        when(patronRepo.existsById(patron.getId())).thenReturn(true);
        when(patronRepo.save(patron)).thenReturn(patron);
        
        // Ejecución
        Patron resultado = patronService.modificar(patron);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(patron, resultado);
        verify(patronRepo).existsById(patron.getId());
        verify(patronRepo).save(patron);
    }
    
    @Test
    void testModificar_PatronNoExiste() {
        // Configuración
        when(patronRepo.existsById(patron.getId())).thenReturn(false);
        
        // Ejecución
        Patron resultado = patronService.modificar(patron);
        
        // Verificación
        assertNull(resultado);
        verify(patronRepo).existsById(patron.getId());
        verify(patronRepo, never()).save(any());
    }
    
    @Test
    void testModificar_ConExcepcion() {
        // Configuración
        when(patronRepo.existsById(patron.getId())).thenReturn(true);
        when(patronRepo.save(patron)).thenThrow(new RuntimeException("Error simulado"));
        
        // Ejecución
        Patron resultado = patronService.modificar(patron);
        
        // Verificación
        assertNull(resultado);
        verify(patronRepo).existsById(patron.getId());
        verify(patronRepo).save(patron);
    }
    
    @Test
    void testEliminar() {
        // Configuración
        when(patronRepo.existsById(patron.getId())).thenReturn(true);
        doNothing().when(patronRepo).deleteById(patron.getId());
        
        // Ejecución
        int resultado = patronService.eliminar(patron);
        
        // Verificación
        assertEquals(1, resultado);
        verify(patronRepo).existsById(patron.getId());
        verify(patronRepo).deleteById(patron.getId());
    }
    
    @Test
    void testEliminarPorId_PatronExiste() {
        // Configuración
        Long id = 1L;
        when(patronRepo.existsById(id)).thenReturn(true);
        doNothing().when(patronRepo).deleteById(id);
        
        // Ejecución
        int resultado = patronService.eliminarPorId(id);
        
        // Verificación
        assertEquals(1, resultado);
        verify(patronRepo).existsById(id);
        verify(patronRepo).deleteById(id);
    }
    
    @Test
    void testEliminarPorId_PatronNoExiste() {
        // Configuración
        Long id = 1L;
        when(patronRepo.existsById(id)).thenReturn(false);
        
        // Ejecución
        int resultado = patronService.eliminarPorId(id);
        
        // Verificación
        assertEquals(0, resultado);
        verify(patronRepo).existsById(id);
        verify(patronRepo, never()).deleteById(any());
    }
    
    @Test
    void testEliminarPorId_ConExcepcion() {
        // Configuración
        Long id = 1L;
        when(patronRepo.existsById(id)).thenReturn(true);
        doThrow(new RuntimeException("Error simulado")).when(patronRepo).deleteById(id);
        
        // Ejecución
        int resultado = patronService.eliminarPorId(id);
        
        // Verificación
        assertEquals(-1, resultado);
        verify(patronRepo).existsById(id);
        verify(patronRepo).deleteById(id);
    }
    
    @Test
    void testBuscarPorId_PatronExiste() {
        // Configuración
        Long id = 1L;
        when(patronRepo.findById(id)).thenReturn(Optional.of(patron));
        
        // Ejecución
        Patron resultado = patronService.buscarPorId(id);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(patron, resultado);
        verify(patronRepo).findById(id);
    }
    
    @Test
    void testBuscarPorId_PatronNoExiste() {
        // Configuración
        Long id = 1L;
        when(patronRepo.findById(id)).thenReturn(Optional.empty());
        
        // Ejecución
        Patron resultado = patronService.buscarPorId(id);
        
        // Verificación
        assertNull(resultado);
        verify(patronRepo).findById(id);
    }
    
    @Test
    void testBuscarTodos() {
        // Configuración
        when(patronRepo.findAll()).thenReturn(patronList);
        
        // Ejecución
        List<Patron> resultado = patronService.buscarTodos();
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(patronList, resultado);
        verify(patronRepo).findAll();
    }
}