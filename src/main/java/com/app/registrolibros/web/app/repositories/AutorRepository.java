package com.app.registrolibros.web.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.registrolibros.web.app.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
