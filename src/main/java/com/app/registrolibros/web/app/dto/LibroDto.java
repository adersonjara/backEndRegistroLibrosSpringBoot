package com.app.registrolibros.web.app.dto;

import java.time.LocalDate;
import java.util.List;

public class LibroDto {
	
	private Long id;
    private String titulo;
    private String descripcion;
    private String ISBN;
    private LocalDate fechaPublicacion;
    private List<AutorDto> autores;
    private TipoLibroDto tipoLibro;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public List<AutorDto> getAutores() {
		return autores;
	}
	public void setAutores(List<AutorDto> autores) {
		this.autores = autores;
	}
	public TipoLibroDto getTipoLibro() {
		return tipoLibro;
	}
	public void setTipoLibro(TipoLibroDto tipoLibro) {
		this.tipoLibro = tipoLibro;
	}
}
