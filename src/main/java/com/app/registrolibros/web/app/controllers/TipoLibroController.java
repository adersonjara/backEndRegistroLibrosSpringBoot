package com.app.registrolibros.web.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.payload.ApiResponse;
import com.app.registrolibros.web.app.services.TipoLibroService;

@RestController
@RequestMapping("/api/tipolibros")
public class TipoLibroController {
	
	@Autowired
	private TipoLibroService tipoLibroService;
	
	@GetMapping
	public ApiResponse<List<TipoLibro>> listar(){
		List<TipoLibro> tiposLibros = tipoLibroService.findAll();
		return new ApiResponse<>("200",tiposLibros,"Lista de todos los libros","");
	}
	
	@GetMapping("/{id}")
	public ApiResponse<TipoLibro> obtenerPorId(@PathVariable Long id) {
		TipoLibro tipoLibro = tipoLibroService.findById(id);
		return new ApiResponse<>("200",tipoLibro,"Información de un solo libro","");
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<TipoLibro> crear(@RequestBody TipoLibro TipoLibro) {
		TipoLibro tipoLibroGuardado = tipoLibroService.save(TipoLibro);
		return new ApiResponse<>("201",tipoLibroGuardado,"Libro Registrado Correctamente","");
	}
	
	@PutMapping("/{id}")
	public ApiResponse<TipoLibro> actualizar(@PathVariable Long id, @RequestBody TipoLibro TipoLibro) {
		TipoLibro tipoLibroActualizado = tipoLibroService.update(id, TipoLibro);
		return new ApiResponse<>("200",tipoLibroActualizado,"Libro Actualizado Correctamente","");
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		tipoLibroService.deleteById(id);
	}
}
