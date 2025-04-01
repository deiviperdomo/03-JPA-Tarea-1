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

import es.eviden.club.entities.Barco;
import es.eviden.club.modelo.service.BarcoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/barco")
@Tag(name = "Barco", description = "Controlador para la gestión de barcos")
public class BarcoRestController {

	 @Autowired
	    private BarcoService bserv;

	    
	    @PostMapping("/alta")
	    @Operation(summary = "Crear un nuevo barco", description = "Crea un nuevo barco en la base de datos")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Código creado exitosamente"),
	        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
	    })
	    public Barco alta(@RequestBody Barco barco) {
	        return bserv.alta(barco);
	    }

	    
	    @PutMapping("/modificar")
	    @Operation(summary = "Modificar un barco", description = "Modifica un barco existente en la base de datos")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Código modificado exitosamente"),
	        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
	        @ApiResponse(responseCode = "404", description = "Código no encontrado")
	    })
	    public Barco modificar(@RequestBody Barco barco) {
	        return bserv.modificar(barco);
	    }

	    
	    @DeleteMapping("/eliminar")
	    @Operation(summary = "Eliminar un barco", description = "Elimina un barco de la base de datos")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Código eliminado exitosamente"),
	        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
	        @ApiResponse(responseCode = "404", description = "Código no encontrado")
	    })
	    public int eliminar(@RequestBody Barco barco) {
	        return bserv.eliminar(barco);
	    }

	    
	    @DeleteMapping("/eliminar/{id}")
	    @Operation(summary = "Eliminar un barco por ID", description = "Elimina un barco de la base de datos utilizando su ID")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Código eliminado exitosamente"),
	        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
	        @ApiResponse(responseCode = "404", description = "Código no encontrado")
	    })
	    public int eliminarPorId(@PathVariable Long id) {
	        return bserv.eliminarPorId(id);
	    }
	    

	    @GetMapping("/buscar/{id}")
	    @Operation(summary = "Buscar un barco por ID", description = "Busca un barco en la base de datos utilizando su ID")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Código encontrado exitosamente"),
	        @ApiResponse(responseCode = "404", description = "Código no encontrado")
	    })
	    public Barco buscarPorId(@PathVariable Long id) {
	        return bserv.buscarPorId(id);
	    }
	    

	    @GetMapping("/todos")
	    @Operation(summary = "Buscar todos los barcos", description = "Busca todos los barcos en la base de datos")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Barcos encontrados exitosamente")
	    })
	    public List<Barco> buscarTodos() {
	        return bserv.buscarTodos();
	    }
	    
	}
