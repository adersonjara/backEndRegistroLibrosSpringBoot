package com.app.registrolibros.web.app.payload;

import org.mapstruct.Mapper;
import com.app.registrolibros.web.app.dto.TipoLibroDto;
import com.app.registrolibros.web.app.entities.TipoLibro;

@Mapper
public interface TipoLibroMapper {
	TipoLibroDto toDto(TipoLibro tipoLibro);

    TipoLibro toEntity(TipoLibroDto tipoLibroDto);
}
