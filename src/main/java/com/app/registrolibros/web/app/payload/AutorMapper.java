package com.app.registrolibros.web.app.payload;

import java.util.List;
import com.app.registrolibros.web.app.dto.AutorDto;
import com.app.registrolibros.web.app.entities.Autor;
import org.mapstruct.Mappings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AutorMapper {
	@Mappings({
	        @Mapping(target = "id", source = "id"),
	        @Mapping(target = "nombre", source = "nombre"),
	        @Mapping(target = "apellido", source = "apellido")
	})
	
	Autor toEntity(AutorDto autorDto);
	
	@Mappings({
	        @Mapping(target = "id", source = "id"),
	        @Mapping(target = "nombre", source = "nombre"),
	        @Mapping(target = "apellido", source = "apellido")
	})
	AutorDto toDto(Autor autor);
	
	List<Autor> toEntityList(List<AutorDto> autorDtoList);
	
	List<AutorDto> toDtoList(List<Autor> autorList);
}
