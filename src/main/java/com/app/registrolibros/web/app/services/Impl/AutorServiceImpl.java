package com.app.registrolibros.web.app.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.registrolibros.web.app.entities.Autor;
import com.app.registrolibros.web.app.repositories.AutorRepository;
import com.app.registrolibros.web.app.services.AutorService;

@Service
public class AutorServiceImpl implements AutorService{
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public List<Autor> findAll(){
		return autorRepository.findAll();
	}
	
	@Override
	public Autor findById(Long id) {
		return autorRepository.findById(id).orElse(null);
	}
	
	@Override
	public Autor save(Autor autor) {
		return autorRepository.save(autor);
	}
	
	@Override
	public Autor update(Long id, Autor autor) {
		Autor autorActual = autorRepository.findById(id).orElse(null);
		
		if(autorActual != null) {
			autorActual.setNombre(autor.getNombre());
			autorActual.setApellido(autor.getApellido());
			autorRepository.save(autorActual);
		}
		
		return autorActual;
	}
	
	@Override
	public void deleteById(Long id) {
		autorRepository.deleteById(id);
	}

}
