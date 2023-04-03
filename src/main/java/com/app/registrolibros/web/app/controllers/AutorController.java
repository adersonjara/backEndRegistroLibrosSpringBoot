package com.app.registrolibros.web.app.controllers;

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
import com.app.registrolibros.web.app.entities.Autor;
import com.app.registrolibros.web.app.payload.ApiResponse;
import com.app.registrolibros.web.app.services.AutorService;

@RestController
@RequestMapping("/api/autores")
public class AutorController {
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Autor> listaAutores = autorService.findAll();
		ApiResponse<List<Autor>> response = new ApiResponse<>("200",listaAutores,"Lista de todos los autores",null);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
		Autor autor = autorService.findById(id);
		if(autor==null) {
			ApiResponse<Autor> response = new ApiResponse<>("404",null,"Autor no encontrado",null);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		ApiResponse<Autor> response = new ApiResponse<>("200",autor,"Información de un solo autor",null);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Autor autor) {
		Autor autorGuardado = autorService.save(autor);
		ApiResponse<Autor> response = new ApiResponse<>("201",autorGuardado,"Autor Registrado Correctamente",null);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Autor autor) {
		Autor autorActualizado = autorService.update(id, autor);
		if(autorActualizado == null) {
			ApiResponse<Autor> response = new ApiResponse<>("404",null,"Autor no encontrado",null);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		ApiResponse<Autor> response = new ApiResponse<>("200",autorActualizado,"Autor Actualizado Correctamente",null);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		autorService.deleteById(id);
	}
}
