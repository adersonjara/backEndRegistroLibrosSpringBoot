package com.app.registrolibros.web.app.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.repositories.TipoLibroRepository;
import com.app.registrolibros.web.app.services.TipoLibroService;

@Service
public class TipoLibroServiceImpl implements TipoLibroService{

	@Autowired
	private TipoLibroRepository tipoLibroRepository;
	
	@Override
	public List<TipoLibro> findAll(){
		return tipoLibroRepository.findAll();
	}
	
	@Override
	public TipoLibro findById(Long id) {
		return tipoLibroRepository.findById(id).orElse(null);
	}
	
	@Override
	public TipoLibro save(TipoLibro tipoLibro) {
		return tipoLibroRepository.save(tipoLibro);
	}
	
	@Override
	public TipoLibro update(Long id, TipoLibro tipoLibro) {
		TipoLibro tipolibroActual = tipoLibroRepository.findById(id).orElse(null);
		
		if(tipolibroActual != null) {
			tipolibroActual.setNombre(tipoLibro.getNombre());
			tipoLibroRepository.save(tipolibroActual);
		}
		
		return tipolibroActual;
	}
	
	@Override
	public void deleteById(Long id) {
		tipoLibroRepository.deleteById(id);
	}
}
