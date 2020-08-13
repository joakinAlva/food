package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.OpcionPlatillo} entity.
 */
public class OpcionPlatilloDTO implements Serializable {
    
    private Long id;

    private String opcionPlatillo;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcionPlatillo() {
        return opcionPlatillo;
    }

    public void setOpcionPlatillo(String opcionPlatillo) {
        this.opcionPlatillo = opcionPlatillo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcionPlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((OpcionPlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcionPlatilloDTO{" +
            "id=" + getId() +
            ", opcionPlatillo='" + getOpcionPlatillo() + "'" +
            "}";
    }
}
