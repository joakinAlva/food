package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.MenuRestaurante} entity.
 */
public class MenuRestauranteDTO implements Serializable {
    
    private Long id;

    private String menuRestaurante;


    private Long restauranteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuRestaurante() {
        return menuRestaurante;
    }

    public void setMenuRestaurante(String menuRestaurante) {
        this.menuRestaurante = menuRestaurante;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuRestauranteDTO)) {
            return false;
        }

        return id != null && id.equals(((MenuRestauranteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuRestauranteDTO{" +
            "id=" + getId() +
            ", menuRestaurante='" + getMenuRestaurante() + "'" +
            ", restauranteId=" + getRestauranteId() +
            "}";
    }
}
