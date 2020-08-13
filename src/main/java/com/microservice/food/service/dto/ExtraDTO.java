package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.Extra} entity.
 */
public class ExtraDTO implements Serializable {
    
    private Long id;

    private String extra;

    private String precio;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExtraDTO)) {
            return false;
        }

        return id != null && id.equals(((ExtraDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExtraDTO{" +
            "id=" + getId() +
            ", extra='" + getExtra() + "'" +
            ", precio='" + getPrecio() + "'" +
            "}";
    }
}
