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
import com.app.registrolibros.web.app.entities.Autor;
import com.app.registrolibros.web.app.payload.ApiResponse;
import com.app.registrolibros.web.app.services.AutorService;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping
	public ApiResponse<List<Autor>> listar(){
		List<Autor> listaAutores = autorService.findAll();
		return new ApiResponse<>("200",listaAutores,"Lista de todos los autores","");
	}
	
	@GetMapping("/{id}")
	public ApiResponse<Autor> obtenerPorId(@PathVariable Long id) {
		Autor autor = autorService.findById(id);
		return new ApiResponse<>("200",autor,"Información de un solo autor","");
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<Autor> crear(@RequestBody Autor autor) {
		Autor autorGuardado = autorService.save(autor);
		return new ApiResponse<>("201",autorGuardado,"Autor Registrado Correctamente","");
	}
	
	@PutMapping("/{id}")
	public ApiResponse<Autor> actualizar(@PathVariable Long id, @RequestBody Autor autor) {
		Autor autorActualizado = autorService.update(id, autor);
		return new ApiResponse<>("200",autorActualizado,"Autor Actualizado Correctamente","");
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		autorService.deleteById(id);
	}
}
