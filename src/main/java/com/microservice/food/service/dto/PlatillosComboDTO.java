package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.PlatillosCombo} entity.
 */
public class PlatillosComboDTO implements Serializable {
    
    private Long id;

    private Double precio;


    private Long platilloId;

    private Long opcionComboId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getPlatilloId() {
        return platilloId;
    }

    public void setPlatilloId(Long platilloId) {
        this.platilloId = platilloId;
    }

    public Long getOpcionComboId() {
        return opcionComboId;
    }

    public void setOpcionComboId(Long opcionComboId) {
        this.opcionComboId = opcionComboId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlatillosComboDTO)) {
            return false;
        }

        return id != null && id.equals(((PlatillosComboDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlatillosComboDTO{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            ", platilloId=" + getPlatilloId() +
            ", opcionComboId=" + getOpcionComboId() +
            "}";
    }
}
