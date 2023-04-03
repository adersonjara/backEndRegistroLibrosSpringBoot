package com.app.registrolibros.web.app.services.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.registrolibros.web.app.dto.TipoLibroDto;
import com.app.registrolibros.web.app.entities.TipoLibro;
import com.app.registrolibros.web.app.repositories.TipoLibroRepository;
import com.app.registrolibros.web.app.services.TipoLibroService;


@Service
public class TipoLibroServiceImpl implements TipoLibroService{

	@Autowired
	private TipoLibroRepository tipoLibroRepository;
	
	@Autowired()
	private ModelMapper modelMapper;
	
	@Override
	public List<TipoLibro> findAll(){
		return tipoLibroRepository.findAll();
	}
	
	@Override
	public TipoLibro findById(Long id) {
		return tipoLibroRepository.findById(id).orElse(null);
	}
	
	@Override
	public TipoLibroDto save(TipoLibroDto tipoLibroDto) {
		TipoLibro tipoLibro = this.modelMapper.map(tipoLibroDto, TipoLibro.class);
		TipoLibro tipoLibroGuardado = this.tipoLibroRepository.save(tipoLibro);
		return this.modelMapper.map(tipoLibroGuardado, TipoLibroDto.class);
	}
	
	@Override
	public TipoLibroDto update(Long id, TipoLibroDto tipoLibroDto) {
		
		TipoLibro tipolibroActual = tipoLibroRepository.findById(id).orElse(null);
		
		if(tipolibroActual != null) {
			tipolibroActual.setNombre(tipoLibroDto.getNombre());
			TipoLibro tipoLibroActualizado = tipoLibroRepository.save(tipolibroActual);
			return this.modelMapper.map(tipoLibroActualizado,TipoLibroDto.class);
		}
		
		return null;
		
	}
	
	@Override
	public void deleteById(Long id) {
		tipoLibroRepository.deleteById(id);
	}
}
