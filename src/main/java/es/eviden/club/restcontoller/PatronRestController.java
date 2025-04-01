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

import es.eviden.club.entities.Patron;
import es.eviden.club.modelo.service.PatronService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/patron")
@Tag(name = "Patron", description = "Controlador para la gestión de patrones")
public class PatronRestController {

	@Autowired
    private PatronService pserv;

    
    @PostMapping("/alta")
    @Operation(summary = "Crear un nuevo patron", description = "Crea un nuevo patron en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patron creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Patron alta(@RequestBody Patron patron) {
        return pserv.alta(patron);
    }

    
    @PutMapping("/modificar")
    @Operation(summary = "Modificar un patron", description = "Modifica un patron existente en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patron modificado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Patron no encontrado")
    })
    public Patron modificar(@RequestBody Patron patron) {
        return pserv.modificar(patron);
    }

    
    @DeleteMapping("/eliminar")
    @Operation(summary = "Eliminar un patron", description = "Elimina un patron de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patron eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Patron no encontrado")
    })
    public int eliminar(@RequestBody Patron patron) {
        return pserv.eliminar(patron);
    }

    
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un patron por ID", description = "Elimina un patron de la base de datos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patron eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Patron no encontrado")
    })
    public int eliminarPorId(@PathVariable Long id) {
        return pserv.eliminarPorId(id);
    }
    

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar un patron por ID", description = "Busca un patron en la base de datos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patron encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Patron no encontrado")
    })
    public Patron buscarPorId(@PathVariable Long id) {
        return pserv.buscarPorId(id);
    }
    

    @GetMapping("/todos")
    @Operation(summary = "Buscar todos los patrones", description = "Busca todos los patrones en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patrones encontrados exitosamente")
    })
    public List<Patron> buscarTodos() {
        return pserv.buscarTodos();
    }
	
}
