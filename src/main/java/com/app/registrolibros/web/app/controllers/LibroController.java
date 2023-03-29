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
import com.app.registrolibros.web.app.dto.LibroDto;
import com.app.registrolibros.web.app.entities.Libro;
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.payload.ApiResponse;
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
	public ApiResponse<List<Libro>> listar(){
		List<Libro> listaLibros = libroService.findAll();
		return new ApiResponse<>("200",listaLibros,"Lista de todos los libros","");
	}
	
	@GetMapping("/{id}")
	public ApiResponse<Libro> obtenerPorId(@PathVariable Long id) {
		Libro libro = libroService.findById(id);
		return new ApiResponse<>("200",libro,"Información de un solo libro","");
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<LibroDto> crearLibro(@RequestBody LibroDto libroDto) {
        LibroDto libroDtoGuardado = libroService.saveDto(libroDto);
        return new ApiResponse<>("201",libroDtoGuardado,"Libro Registrado Correctamente","");
    }
	
	@PostMapping("/{tipoLibroId}/tipolibro")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<Libro> crear(@RequestBody Libro libro,@PathVariable Long tipoLibroId) {
		TipoLibro tipoLibro = tipoLibroService.findById(tipoLibroId);
		libro.setTipoLibro(tipoLibro);
		Libro libroGuardado = libroService.save(libro);
		return new ApiResponse<>("201",libroGuardado,"Libro Registrado Correctamente","");
	}
	
	@PutMapping("/{id}")
	public ApiResponse<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libro) {
		Libro libroActualizado = libroService.update(id, libro);
		return new ApiResponse<>("200",libroActualizado,"Libro Actualizado Correctamente","");
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		libroService.deleteById(id);
	}
}
