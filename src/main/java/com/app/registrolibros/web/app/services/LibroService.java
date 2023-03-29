package com.app.registrolibros.web.app.services;

import java.util.List;
import com.app.registrolibros.web.app.dto.LibroDto;
import com.app.registrolibros.web.app.entities.Libro;

public interface LibroService {
	
	List<Libro> findAll();
	
	Libro findById(Long id);
	
	Libro save(Libro libro);
	
	LibroDto updateDto(Long id, LibroDto libroDto);
	
	void deleteById(Long id);
	
	LibroDto saveDto(LibroDto librodto);
	
}
