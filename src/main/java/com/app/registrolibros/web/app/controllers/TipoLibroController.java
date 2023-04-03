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
import com.app.registrolibros.web.app.dto.TipoLibroDto;
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.payload.ApiResponse;
import com.app.registrolibros.web.app.payload.CampoError;
import com.app.registrolibros.web.app.services.TipoLibroService;

@RestController
@RequestMapping("/api/tipolibros")
public class TipoLibroController {
	
	@Autowired
	private TipoLibroService tipoLibroService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<TipoLibro> tiposLibros = tipoLibroService.findAll();
		ApiResponse<List<TipoLibro>> response = new ApiResponse<>("200",tiposLibros,"Lista de todos los libros",null);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		TipoLibro tipoLibro = tipoLibroService.findById(id);
		if(tipoLibro != null) {
			ApiResponse<TipoLibro> response = new ApiResponse<>("200",tipoLibro,"Información de un solo libro",null);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		ApiResponse<TipoLibro> response = new ApiResponse<>("404",null,"Libro no encontrado",null);
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody TipoLibroDto tipoLibroDto) {
		List<CampoError> errores = new ArrayList<>();
		
		try {
			TipoLibroDto tipoLibroGuardado = tipoLibroService.save(tipoLibroDto);
			
			ApiResponse<TipoLibroDto> response = new ApiResponse<>("201",tipoLibroGuardado,"Libro Registrado Correctamente",null);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
	            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
	            if (constraintViolationException.getConstraintName().contains("UK_3n17m44qh8ut6vt36t9u8966g")) {
					errores.add(new CampoError("nombre", "El nombre ya existe"));
	            }
	        }
	        
	        if (!errores.isEmpty()) {
	            ApiResponse<List<CampoError>> response = new ApiResponse<>("400", null, "Solicitud Incorrecta", errores);
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	        
	        ApiResponse<TipoLibroDto> response = new ApiResponse<>("500", null, "Error interno del servidor", null);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoLibroDto tipoLibroDto) {
		List<CampoError> errores = new ArrayList<>();
		
		try {
			TipoLibroDto tipoLibroActualizado = tipoLibroService.update(id, tipoLibroDto);
			if(tipoLibroActualizado == null) {
				ApiResponse<TipoLibroDto> response = new ApiResponse<>("404",null,"Tipo Libro no encontrado",null);
		        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			ApiResponse<TipoLibroDto> response = new ApiResponse<>("200",tipoLibroActualizado,"Tipo Libro Actualizado Correctamente",null);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
		}catch(DataIntegrityViolationException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
	            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
	            if (constraintViolationException.getConstraintName().contains("UK_3n17m44qh8ut6vt36t9u8966g")) {
					errores.add(new CampoError("nombre", "El nombre ya existe"));
	            }
	        }
	        
	        if (!errores.isEmpty()) {
	            ApiResponse<List<CampoError>> response = new ApiResponse<>("400", null, "Solicitud Incorrecta", errores);
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	        
	        ApiResponse<TipoLibroDto> response = new ApiResponse<>("500", null, "Error interno del servidor", null);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		tipoLibroService.deleteById(id);
	}
}
