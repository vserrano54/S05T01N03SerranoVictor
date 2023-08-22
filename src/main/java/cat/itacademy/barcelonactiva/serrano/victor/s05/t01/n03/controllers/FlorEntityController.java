package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n03.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n03.model.dto.FlorEntityDto;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n03.model.services.FlorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "FlorEntityDto", description = "API lectura, escritura, inserción y actualización de registro de la entidad FlorEntityDto")
@RestController
//@Slf4j
@RequestMapping("api/v1/flor")
public class FlorEntityController {
	
	@Autowired
	private FlorService florservice;
	
	  @Operation(summary = "Obtener lista de registros",
			  description="Obtiene el listado del catalogo de FlorEntity, no se reciben parametros")
	  @ApiResponses(value = { 
		      @ApiResponse(responseCode = "200", description = "Resitro encontrado",content= {@Content(mediaType="application/json",schema=@Schema(implementation=FlorEntityDto.class))}),
		      @ApiResponse(responseCode = "404", description = "Registro no encontrado", content=@Content), 
		      @ApiResponse(responseCode = "405", description = "Método no permitido", content=@Content),
		      @ApiResponse(responseCode = "500", description = "Error interno", content=@Content)})
	@GetMapping
	public ResponseEntity<List<FlorEntityDto>> readAll(){
	
		  List<FlorEntityDto> flores =null;
		  try {
			  flores = florservice.findAll();
		        
		    } catch (HttpClientErrorException e) {
		        // Capturar excepción 405 (Method Not Allowed)
		        if (e.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED) {
		            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		        }
		        
		        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		        
		        // Otras excepciones HttpClientErrorException pueden manejarse aquí
		    } catch (Exception e) {
		        // Capturar excepciones genéricas y manejarlas como un error interno (500)
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  return new ResponseEntity<>(flores, HttpStatus.OK);
	}
	  
	  
	  @Operation(summary = "Obtener un registro",
			  description="Obtener registro de FlorEntityDto, se debe de ingresar el id como parametro de entrada")
	  @ApiResponses(value = { 
		      @ApiResponse(responseCode = "200", description = "Resitro encontrado",content= {@Content(mediaType="application/json",schema=@Schema(implementation=FlorEntityDto.class))}),
		      @ApiResponse(responseCode = "404", description = "Registro no encontrado", content=@Content), 
		      @ApiResponse(responseCode = "405", description = "Método no permitido", content=@Content),
		      @ApiResponse(responseCode = "500", description = "Error interno", content=@Content)})
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") int id) {
		     
		  FlorEntityDto flor= null;
		  try {
			 flor = florservice.findById(id);
			 
			
		  } catch (HttpClientErrorException e) {
		        if (e.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED) {//405
		            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		        }
		        
		        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {  //404
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		        
		    } catch (Exception e) {
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		    }  
		  
		  return new ResponseEntity<>(flor, HttpStatus.OK);	
		   
	}

	@Operation(summary = "Guardar un registro",
			description="Guarda registro de una entidad FlorEntityDto, se ingresa como parametro la entidad")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "201", description = "Solicitud procesada forma exitosa",content= {@Content(mediaType="application/json",schema=@Schema(implementation=FlorEntityDto.class))}),
    		@ApiResponse(responseCode = "405", description = "Método no permitido", content=@Content),
			@ApiResponse(responseCode = "500", description = "Error interno", content=@Content)})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody FlorEntityDto flor) {
	    try {
	        
	        florservice.save(flor);
	    } catch (Exception e) {
	        
	        throw new RuntimeException("Error al guardar la flor", e);
	    }
	}
	
	@Operation(summary = "Actualizar un registro",
			description="Actualizar un registro de una entidad FlorEntityDto, se ingresa como parametro la entidad y su id")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "204", description = "Solicitud procesada de forma exitosa",content= {@Content(mediaType="application/json",schema=@Schema(implementation=FlorEntityDto.class))}),
    		@ApiResponse(responseCode = "405", description = "Método no permitido", content=@Content),
			@ApiResponse(responseCode = "500", description = "Error interno", content=@Content)})
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Integer id,@RequestBody FlorEntityDto florDetalle) {	
	try {
		        
			florservice.update(id, florDetalle);
		} catch (Exception e) {
		        
			throw new RuntimeException("Error al actualizar el registro", e);
		}
	
	}
	
	@Operation(summary = "Eliminar un registro",
			description="Eliminar un registro de una entidad FlorEntityDto, se ingresa como parametro el id")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "204", description = "Solicitud procesada de forma exitosa",content= {@Content(mediaType="application/json",schema=@Schema(implementation=FlorEntityDto.class))}),
    		@ApiResponse(responseCode = "405", description = "Método no permitido", content=@Content),
			@ApiResponse(responseCode = "500", description = "Error interno", content=@Content)})
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable("id") Integer id){
	
		try {
	        
			florservice.deleteById(id);
		} catch (Exception e) {
		        
			throw new RuntimeException("Error al intentar eliminar el registro", e);
		}
		
	}
	

}
