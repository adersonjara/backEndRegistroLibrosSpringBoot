package com.app.registrolibros.web.app.services;

import java.util.List;
import com.app.registrolibros.web.app.entities.TipoLibro;

public interface TipoLibroService {
	
	List<TipoLibro> findAll();
	
	TipoLibro findById(Long id);
	
	TipoLibro save(TipoLibro tipoLibro);
	
	TipoLibro update(Long id, TipoLibro tipoLibro);
	
	void deleteById(Long id);
	
}
