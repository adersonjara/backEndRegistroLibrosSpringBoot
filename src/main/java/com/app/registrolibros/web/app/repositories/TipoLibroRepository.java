package com.app.registrolibros.web.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.registrolibros.web.app.entities.TipoLibro;

public interface TipoLibroRepository extends JpaRepository<TipoLibro, Long>{

}
