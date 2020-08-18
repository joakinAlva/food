package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.OpcionCombo} entity.
 */
public class OpcionComboDTO implements Serializable {
    
    private Long id;

    private String opcionCombo;

    private String cnatidad;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcionCombo() {
        return opcionCombo;
    }

    public void setOpcionCombo(String opcionCombo) {
        this.opcionCombo = opcionCombo;
    }

    public String getCnatidad() {
        return cnatidad;
    }

    public void setCnatidad(String cnatidad) {
        this.cnatidad = cnatidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcionComboDTO)) {
            return false;
        }

        return id != null && id.equals(((OpcionComboDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcionComboDTO{" +
            "id=" + getId() +
            ", opcionCombo='" + getOpcionCombo() + "'" +
            ", cnatidad='" + getCnatidad() + "'" +
            "}";
    }
}
