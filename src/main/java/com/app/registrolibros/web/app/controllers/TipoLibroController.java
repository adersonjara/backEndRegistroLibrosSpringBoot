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
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.services.TipoLibroService;

@RestController
@RequestMapping("/api/tipolibros")
public class TipoLibroController {
	
	@Autowired
	private TipoLibroService tipoLibroService;
	
	@GetMapping
	public List<TipoLibro> listar(){
		return tipoLibroService.findAll();
	}
	
	@GetMapping("/{id}")
	public TipoLibro obtenerPorId(@PathVariable Long id) {
		return tipoLibroService.findById(id);
	}
	
	@PostMapping
	public TipoLibro crear(@RequestBody TipoLibro TipoLibro) {
		return tipoLibroService.save(TipoLibro);
	}
	
	@PutMapping("/{id}")
	public TipoLibro actualizar(@PathVariable Long id, @RequestBody TipoLibro TipoLibro) {
		return tipoLibroService.update(id, TipoLibro);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		tipoLibroService.deleteById(id);
	}
}
