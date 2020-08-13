package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.OpcionesPlatillos} entity.
 */
public class OpcionesPlatillosDTO implements Serializable {
    
    private Long id;

    private String descripcionOpcion;

    private String precio;


    private Long opcionPlatilloId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionOpcion() {
        return descripcionOpcion;
    }

    public void setDescripcionOpcion(String descripcionOpcion) {
        this.descripcionOpcion = descripcionOpcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Long getOpcionPlatilloId() {
        return opcionPlatilloId;
    }

    public void setOpcionPlatilloId(Long opcionPlatilloId) {
        this.opcionPlatilloId = opcionPlatilloId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcionesPlatillosDTO)) {
            return false;
        }

        return id != null && id.equals(((OpcionesPlatillosDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcionesPlatillosDTO{" +
            "id=" + getId() +
            ", descripcionOpcion='" + getDescripcionOpcion() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", opcionPlatilloId=" + getOpcionPlatilloId() +
            "}";
    }
}
