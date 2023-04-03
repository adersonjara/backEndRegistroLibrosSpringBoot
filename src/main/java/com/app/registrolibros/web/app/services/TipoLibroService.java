package com.app.registrolibros.web.app.services;

import java.util.List;
import com.app.registrolibros.web.app.dto.TipoLibroDto;
import com.app.registrolibros.web.app.entities.TipoLibro;

public interface TipoLibroService {
	
	List<TipoLibro> findAll();
	
	TipoLibro findById(Long id);
	
	TipoLibroDto save(TipoLibroDto tipoLibroDto);
	
	TipoLibroDto update(Long id, TipoLibroDto tipoLibroDto);
	
	void deleteById(Long id);
	
}
