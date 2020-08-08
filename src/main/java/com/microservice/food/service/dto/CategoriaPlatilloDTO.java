package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.CategoriaPlatillo} entity.
 */
public class CategoriaPlatilloDTO implements Serializable {
    
    private Long id;

    private String categoria;

    private String imagenSrc;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagenSrc() {
        return imagenSrc;
    }

    public void setImagenSrc(String imagenSrc) {
        this.imagenSrc = imagenSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoriaPlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((CategoriaPlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoriaPlatilloDTO{" +
            "id=" + getId() +
            ", categoria='" + getCategoria() + "'" +
            ", imagenSrc='" + getImagenSrc() + "'" +
            "}";
    }
}
