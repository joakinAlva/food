package com.microservice.food.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.Platillo} entity.
 */
@ApiModel(description = "not an ignored comment")
public class PlatilloDTO implements Serializable {
    
    private Long id;

    private String nombre;

    private String descripcion;

    private String fotoSrc;

    private String horario;

    private Long precio;


    private Long restauranteId;

    private Long categoriaPlatilloId;

    private Long tipoPlatilloId;
    
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

    public String getFotoSrc() {
        return fotoSrc;
    }

    public void setFotoSrc(String fotoSrc) {
        this.fotoSrc = fotoSrc;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public Long getCategoriaPlatilloId() {
        return categoriaPlatilloId;
    }

    public void setCategoriaPlatilloId(Long categoriaPlatilloId) {
        this.categoriaPlatilloId = categoriaPlatilloId;
    }

    public Long getTipoPlatilloId() {
        return tipoPlatilloId;
    }

    public void setTipoPlatilloId(Long tipoPlatilloId) {
        this.tipoPlatilloId = tipoPlatilloId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((PlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlatilloDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", fotoSrc='" + getFotoSrc() + "'" +
            ", horario='" + getHorario() + "'" +
            ", precio=" + getPrecio() +
            ", restauranteId=" + getRestauranteId() +
            ", categoriaPlatilloId=" + getCategoriaPlatilloId() +
            ", tipoPlatilloId=" + getTipoPlatilloId() +
            "}";
    }
}
