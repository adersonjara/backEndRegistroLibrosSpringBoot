package com.app.registrolibros.web.app.services;

import java.util.List;
import com.app.registrolibros.web.app.entities.Autor;

public interface AutorService {
	
	List<Autor> findAll();
	
	Autor findById(Long id);
	
	Autor save(Autor autor);
	
	Autor update(Long id, Autor autor);
	
	void deleteById(Long id);
		
}
