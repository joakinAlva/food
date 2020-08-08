package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.Restaurante} entity.
 */
public class RestauranteDTO implements Serializable {
    
    private Long id;

    private String nombre;

    private String descripcion;

    private String direccion;

    private Long calificacion;

    private String imagenFondoSrc;

    private String imagenPerfilSrc;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Long calificacion) {
        this.calificacion = calificacion;
    }

    public String getImagenFondoSrc() {
        return imagenFondoSrc;
    }

    public void setImagenFondoSrc(String imagenFondoSrc) {
        this.imagenFondoSrc = imagenFondoSrc;
    }

    public String getImagenPerfilSrc() {
        return imagenPerfilSrc;
    }

    public void setImagenPerfilSrc(String imagenPerfilSrc) {
        this.imagenPerfilSrc = imagenPerfilSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestauranteDTO)) {
            return false;
        }

        return id != null && id.equals(((RestauranteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestauranteDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", calificacion=" + getCalificacion() +
            ", imagenFondoSrc='" + getImagenFondoSrc() + "'" +
            ", imagenPerfilSrc='" + getImagenPerfilSrc() + "'" +
            "}";
    }
}
