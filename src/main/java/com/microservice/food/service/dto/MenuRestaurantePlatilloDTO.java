package com.microservice.food.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.microservice.food.domain.MenuRestaurantePlatillo} entity.
 */
public class MenuRestaurantePlatilloDTO implements Serializable {
    
    private Long id;

    private String tipoPlatillo;

    private Set<PlatilloDTO> platillos = new HashSet<>();

    private Long menuRestauranteId;
    
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

    public Set<PlatilloDTO> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(Set<PlatilloDTO> platillos) {
        this.platillos = platillos;
    }

    public Long getMenuRestauranteId() {
        return menuRestauranteId;
    }

    public void setMenuRestauranteId(Long menuRestauranteId) {
        this.menuRestauranteId = menuRestauranteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuRestaurantePlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((MenuRestaurantePlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuRestaurantePlatilloDTO{" +
            "id=" + getId() +
            ", tipoPlatillo='" + getTipoPlatillo() + "'" +
            ", platillos='" + getPlatillos() + "'" +
            ", menuRestauranteId=" + getMenuRestauranteId() +
            "}";
    }
}
