package es.eviden.club.restcontoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eviden.club.entities.Socio;
import es.eviden.club.modelo.service.SocioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/socio")
@Tag(name = "Socio", description = "Controlador para la gestión de socios")
public class SocioRestController {

	 @Autowired
    private SocioService cserv;

    
    @PostMapping("/alta")
    @Operation(summary = "Crear un nuevo socio", description = "Crea un nuevo socio en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Código creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Socio alta(@RequestBody Socio socio) {
        return cserv.alta(socio);
    }

    
    @PutMapping("/modificar")
    @Operation(summary = "Modificar un socio", description = "Modifica un socio existente en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Código modificado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    public Socio modificar(@RequestBody Socio socio) {
        return cserv.modificar(socio);
    }

    
    @DeleteMapping("/eliminar")
    @Operation(summary = "Eliminar un socio", description = "Elimina un socio de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Código eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    public int eliminar(@RequestBody Socio socio) {
        return cserv.eliminar(socio);
    }

    
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un socio por ID", description = "Elimina un socio de la base de datos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Código eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    public int eliminarPorId(@PathVariable Long id) {
        return cserv.eliminarPorId(id);
    }
    

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar un socio por ID", description = "Busca un socio en la base de datos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Código encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    public Socio buscarPorId(@PathVariable Long id) {
        return cserv.buscarPorId(id);
    }
    

    @GetMapping("/todos")
    @Operation(summary = "Buscar todos los socios", description = "Busca todos los socios en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Socios encontrados exitosamente")
    })
    public List<Socio> buscarTodos() {
        return cserv.buscarTodos();
    }
	    
}


