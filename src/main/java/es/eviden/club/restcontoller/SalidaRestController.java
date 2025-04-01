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

import es.eviden.club.entities.Salida;
import es.eviden.club.modelo.service.SalidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/salida")
@Tag(name = "Salida", description = "Controlador para la gestión de salidas")
public class SalidaRestController {

	@Autowired
    private SalidaService salidaService;

    
    @PostMapping("/alta")
    @Operation(summary = "Crear una nuevo salida", description = "Crea una nuevo salida en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salida creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Salida alta(@RequestBody Salida salida) {
        return salidaService.alta(salida);
    }

    
    @PutMapping("/modificar")
    @Operation(summary = "Modificar una salida", description = "Modifica una salida existente en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salida modificado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Salida no encontrado")
    })
    public Salida modificar(@RequestBody Salida salida) {
        return salidaService.modificar(salida);
    }

    
    @DeleteMapping("/eliminar")
    @Operation(summary = "Eliminar una salida", description = "Elimina una salida de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salida eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Salida no encontrado")
    })
    public int eliminar(@RequestBody Salida salida) {
        return salidaService.eliminar(salida);
    }

    
    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar una salida por ID", description = "Elimina una salida de la base de datos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salida eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Salida no encontrado")
    })
    public int eliminarPorId(@PathVariable Long id) {
        return salidaService.eliminarPorId(id);
    }
    

    @GetMapping("/buscar/{id}")
    @Operation(summary = "Buscar una salida por ID", description = "Busca una salida en la base de datos utilizando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salida encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Salida no encontrado")
    })
    public Salida buscarPorId(@PathVariable Long id) {
        return salidaService.buscarPorId(id);
    }
    

    @GetMapping("/todos")
    @Operation(summary = "Buscar todos las salidas", description = "Busca todos las salidas en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Salidas encontrados exitosamente")
    })
    public List<Salida> buscarTodos() {
        return salidaService.buscarTodos();
    }
	
}
