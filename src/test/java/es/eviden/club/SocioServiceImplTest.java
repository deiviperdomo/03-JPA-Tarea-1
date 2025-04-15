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

import es.eviden.club.entities.Socio;
import es.eviden.club.modelo.rpository.SocioRepository;
import es.eviden.club.modelo.service.SocioServiceImpl;

@ExtendWith(MockitoExtension.class)
class SocioServiceImplTest {

    @Mock
    private SocioRepository socioRepo;
    
    @InjectMocks
    private SocioServiceImpl socioService;
    
    private Socio socio;
    private List<Socio> socioList;
    
    @BeforeEach
    void setUp() {
        // Configurar datos de prueba
        socio = new Socio();
        socio.setId(1L);
        // Configurar otros atributos necesarios de Socio
        
        socioList = new ArrayList<>();
        socioList.add(socio);
    }
    
    @Test
    void testAlta_SocioNoExiste() {
        // Configuración
        when(socioRepo.existsById(socio.getId())).thenReturn(false);
        when(socioRepo.save(socio)).thenReturn(socio);
        
        // Ejecución
        Socio resultado = socioService.alta(socio);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(socio, resultado);
        verify(socioRepo).existsById(socio.getId());
        verify(socioRepo).save(socio);
    }
    
    @Test
    void testAlta_SocioYaExiste() {
        // Configuración
        when(socioRepo.existsById(socio.getId())).thenReturn(true);
        
        // Ejecución
        Socio resultado = socioService.alta(socio);
        
        // Verificación
        assertNull(resultado);
        verify(socioRepo).existsById(socio.getId());
        verify(socioRepo, never()).save(any());
    }
    
    @Test
    void testModificar_SocioExiste() {
        // Configuración
        when(socioRepo.existsById(socio.getId())).thenReturn(true);
        when(socioRepo.save(socio)).thenReturn(socio);
        
        // Ejecución
        Socio resultado = socioService.modificar(socio);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(socio, resultado);
        verify(socioRepo).existsById(socio.getId());
        verify(socioRepo).save(socio);
    }
    
    @Test
    void testModificar_SocioNoExiste() {
        // Configuración
        when(socioRepo.existsById(socio.getId())).thenReturn(false);
        
        // Ejecución
        Socio resultado = socioService.modificar(socio);
        
        // Verificación
        assertNull(resultado);
        verify(socioRepo).existsById(socio.getId());
        verify(socioRepo, never()).save(any());
    }
    
    @Test
    void testModificar_ConExcepcion() {
        // Configuración
        when(socioRepo.existsById(socio.getId())).thenReturn(true);
        when(socioRepo.save(socio)).thenThrow(new RuntimeException("Error simulado"));
        
        // Ejecución
        Socio resultado = socioService.modificar(socio);
        
        // Verificación
        assertNull(resultado);
        verify(socioRepo).existsById(socio.getId());
        verify(socioRepo).save(socio);
    }
    
    @Test
    void testEliminar() {
        // Configuración
        when(socioRepo.existsById(socio.getId())).thenReturn(true);
        doNothing().when(socioRepo).deleteById(socio.getId());
        
        // Ejecución
        int resultado = socioService.eliminar(socio);
        
        // Verificación
        assertEquals(1, resultado);
        verify(socioRepo).existsById(socio.getId());
        verify(socioRepo).deleteById(socio.getId());
    }
    
    @Test
    void testEliminarPorId_SocioExiste() {
        // Configuración
        Long id = 1L;
        when(socioRepo.existsById(id)).thenReturn(true);
        doNothing().when(socioRepo).deleteById(id);
        
        // Ejecución
        int resultado = socioService.eliminarPorId(id);
        
        // Verificación
        assertEquals(1, resultado);
        verify(socioRepo).existsById(id);
        verify(socioRepo).deleteById(id);
    }
    
    @Test
    void testEliminarPorId_SocioNoExiste() {
        // Configuración
        Long id = 1L;
        when(socioRepo.existsById(id)).thenReturn(false);
        
        // Ejecución
        int resultado = socioService.eliminarPorId(id);
        
        // Verificación
        assertEquals(0, resultado);
        verify(socioRepo).existsById(id);
        verify(socioRepo, never()).deleteById(any());
    }
    
    @Test
    void testEliminarPorId_ConExcepcion() {
        // Configuración
        Long id = 1L;
        when(socioRepo.existsById(id)).thenReturn(true);
        doThrow(new RuntimeException("Error simulado")).when(socioRepo).deleteById(id);
        
        // Ejecución
        int resultado = socioService.eliminarPorId(id);
        
        // Verificación
        assertEquals(-1, resultado);
        verify(socioRepo).existsById(id);
        verify(socioRepo).deleteById(id);
    }
    
    @Test
    void testBuscarPorId_SocioExiste() {
        // Configuración
        Long id = 1L;
        when(socioRepo.findById(id)).thenReturn(Optional.of(socio));
        
        // Ejecución
        Socio resultado = socioService.buscarPorId(id);
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(socio, resultado);
        verify(socioRepo).findById(id);
    }
    
    @Test
    void testBuscarPorId_SocioNoExiste() {
        // Configuración
        Long id = 1L;
        when(socioRepo.findById(id)).thenReturn(Optional.empty());
        
        // Ejecución
        Socio resultado = socioService.buscarPorId(id);
        
        // Verificación
        assertNull(resultado);
        verify(socioRepo).findById(id);
    }
    
    @Test
    void testBuscarTodos() {
        // Configuración
        when(socioRepo.findAll()).thenReturn(socioList);
        
        // Ejecución
        List<Socio> resultado = socioService.buscarTodos();
        
        // Verificación
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(socioList, resultado);
        verify(socioRepo).findAll();
    }
}