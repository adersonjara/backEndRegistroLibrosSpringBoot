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
import com.app.registrolibros.web.app.entities.Autor;
import com.app.registrolibros.web.app.services.AutorService;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping
	public List<Autor> listar(){
		return autorService.findAll();
	}
	
	@GetMapping("/{id}")
	public Autor obtenerPorId(@PathVariable Long id) {
		return autorService.findById(id);
	}
	
	@PostMapping
	public Autor crear(@RequestBody Autor autor) {
		return autorService.save(autor);
	}
	
	@PutMapping("/{id}")
	public Autor actualizar(@PathVariable Long id, @RequestBody Autor autor) {
		return autorService.update(id, autor);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		autorService.deleteById(id);
	}
}
