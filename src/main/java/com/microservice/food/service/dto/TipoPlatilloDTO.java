package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.TipoPlatillo} entity.
 */
public class TipoPlatilloDTO implements Serializable {
    
    private Long id;

    private String tipoPlatillo;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPlatillo() {
        return tipoPlatillo;
    }

    public void setTipoPlatillo(String tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoPlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((TipoPlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoPlatilloDTO{" +
            "id=" + getId() +
            ", tipoPlatillo='" + getTipoPlatillo() + "'" +
            "}";
    }
}
