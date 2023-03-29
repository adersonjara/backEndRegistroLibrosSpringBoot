package com.app.registrolibros.web.app.services.Impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.registrolibros.web.app.dto.LibroDto;
import com.app.registrolibros.web.app.entities.Libro;
import com.app.registrolibros.web.app.repositories.LibroRepository;
import com.app.registrolibros.web.app.services.LibroService;

@Service
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Libro> findAll(){
		return libroRepository.findAll();
	}
	
	@Override
	public Libro findById(Long id) {
		return libroRepository.findById(id).orElse(null);
	}
	
	@Override
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}
	
	@Override
	public LibroDto saveDto(LibroDto libroDto) {
		Libro libro = modelMapper.map(libroDto, Libro.class);
	    libro = libroRepository.save(libro);
	    return modelMapper.map(libro, LibroDto.class);
	}
	
	@Override
	public Libro update(Long id, Libro libro) {
		Libro libroActual = libroRepository.findById(id).orElse(null);
		
		if(libroActual != null) {
			libroActual.setTitulo(libro.getTitulo());
			libroActual.setISBN(libro.getISBN());
			libroActual.setFechaPublicacion(libro.getFechaPublicacion());
			libroActual.setTipoLibro(libro.getTipoLibro());
			libroActual.setAutores(libro.getAutores());
			
			libroRepository.save(libroActual);
		}
		
		return libroActual;
	}
	
	@Override
	public void deleteById(Long id) {
		libroRepository.deleteById(id);
	}
}
