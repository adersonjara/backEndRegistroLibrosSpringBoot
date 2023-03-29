package com.app.registrolibros.web.app.controllers;

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
import com.app.registrolibros.web.app.dto.LibroDto;
import com.app.registrolibros.web.app.entities.Libro;
import com.app.registrolibros.web.app.entities.TipoLibro;
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
	public List<Libro> listar(){
		return libroService.findAll();
	}
	
	@GetMapping("/{id}")
	public Libro obtenerPorId(@PathVariable Long id) {
		return libroService.findById(id);
	}
	
	@PostMapping
	public LibroDto crearLibro(@RequestBody LibroDto libroDto) {
        LibroDto libroDtoCreado = libroService.saveDto(libroDto);
        return libroDtoCreado;
    }
	
	@PostMapping("/{tipoLibroId}/tipolibro")
	public Libro crear(@RequestBody Libro libro,@PathVariable Long tipoLibroId) {
		TipoLibro tipoLibro = tipoLibroService.findById(tipoLibroId);
		libro.setTipoLibro(tipoLibro);
		return libroService.save(libro);
	}
	
	@PutMapping("/{id}")
	public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
		return libroService.update(id, libro);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		libroService.deleteById(id);
	}
}
