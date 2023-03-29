package com.app.registrolibros.web.app.services.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.registrolibros.web.app.dto.AutorDto;
import com.app.registrolibros.web.app.dto.LibroDto;
import com.app.registrolibros.web.app.entities.Autor;
import com.app.registrolibros.web.app.entities.Libro;
import com.app.registrolibros.web.app.payload.AutorMapper;
import com.app.registrolibros.web.app.payload.TipoLibroMapper;
import com.app.registrolibros.web.app.repositories.LibroRepository;
import com.app.registrolibros.web.app.services.LibroService;

@Service
public class LibroServiceImpl implements LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
		
	AutorMapper autorMapper = Mappers.getMapper(AutorMapper.class);
	
	TipoLibroMapper tipoLibroMapper = Mappers.getMapper(TipoLibroMapper.class);
	
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
	public LibroDto updateDto(Long id, LibroDto libroDto) {
		
		Libro libroActual = libroRepository.findById(id).orElse(null);
		
		if(libroActual != null) {
			
			libroActual.setDescripcion(libroDto.getDescripcion());
			libroActual.setTitulo(libroDto.getTitulo());
			libroActual.setISBN(libroDto.getISBN());
			libroActual.setFechaPublicacion(libroDto.getFechaPublicacion());
			libroActual.setTipoLibro(tipoLibroMapper.toEntity(libroDto.getTipoLibro()));
			
			Set<Autor> autores = new HashSet<>();
		    for (AutorDto autorDto : libroDto.getAutores()) {
		        autores.add(autorMapper.toEntity(autorDto));
		    }
		    libroActual.setAutores(autores);
		    
			
			libroRepository.save(libroActual);
			
			
		}
		
		return modelMapper.map(libroActual, LibroDto.class);
		
		
	}
	
	@Override
	public void deleteById(Long id) {
		libroRepository.deleteById(id);
	}
}
