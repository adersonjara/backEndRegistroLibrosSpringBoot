package com.app.registrolibros.web.app.controllers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.app.registrolibros.web.app.dto.LibroDto;
import com.app.registrolibros.web.app.entities.Libro;
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.payload.ApiResponse;
import com.app.registrolibros.web.app.payload.CampoError;
import com.app.registrolibros.web.app.services.LibroService;
import com.app.registrolibros.web.app.services.TipoLibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private TipoLibroService tipoLibroService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Libro> listaLibros = libroService.findAll();
		ApiResponse<List<Libro>> response = new ApiResponse<>("200",listaLibros,"Lista de todos los libros",null);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		Libro libro = libroService.findById(id);
		if(libro != null) {
			ApiResponse<Libro> response = new ApiResponse<>("200",libro,"Información de un solo libro",null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		ApiResponse<Libro> response = new ApiResponse<>("404",null,"Libro no encontrado",null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> crearLibro(@RequestBody LibroDto libroDto) {
		List<CampoError> errores = new ArrayList<>();
		
		if (libroDto.getTipoLibro() == null) {
	        errores.add(new CampoError("tipoLibro", "El tipo de libro es obligatorio"));
	    }

	    if (libroDto.getAutores() == null || libroDto.getAutores().isEmpty()) {
	        errores.add(new CampoError("autores", "Se requiere al menos un autor"));
	    }

	    if (!errores.isEmpty()) {
	        ApiResponse<List<CampoError>> response = new ApiResponse<>("400", null, "Solicitud Incorrecta", errores);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
		
		try {
			LibroDto libroDtoGuardado = libroService.saveDto(libroDto);
	        ApiResponse<LibroDto> response = new ApiResponse<>("201",libroDtoGuardado,"Libro Registrado Correctamente",null);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
	        if (e.getCause() instanceof ConstraintViolationException) {
	            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
	            if (constraintViolationException.getConstraintName().contains("UK_ehuya6b4bxgkc4ru5wcf5njgr")) {
					errores.add(new CampoError("ISBN", "El ISBN ya existe"));
	            }
	        }
	        
	        if (!errores.isEmpty()) {
	            ApiResponse<List<CampoError>> response = new ApiResponse<>("400", null, "Solicitud Incorrecta", errores);
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	        
	        ApiResponse<LibroDto> response = new ApiResponse<>("500", null, "Error interno del servidor", null);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }  
    }
	
	@PostMapping("/{tipoLibroId}/tipolibro")
	public ResponseEntity<?> crear(@RequestBody Libro libro,@PathVariable Long tipoLibroId) {
		TipoLibro tipoLibro = tipoLibroService.findById(tipoLibroId);
		libro.setTipoLibro(tipoLibro);
		Libro libroGuardado = libroService.save(libro);
		ApiResponse<Libro> response = new ApiResponse<>("201",libroGuardado,"Libro Registrado Correctamente",null);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody LibroDto libroDto) {
		LibroDto libroActualizado = libroService.updateDto(id, libroDto);
		ApiResponse<LibroDto> response = new ApiResponse<>("200",libroActualizado,"Libro Actualizado Correctamente",null);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		libroService.deleteById(id);
	}
}
