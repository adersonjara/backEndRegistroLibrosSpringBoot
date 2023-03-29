package com.app.registrolibros.web.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.registrolibros.web.app.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
