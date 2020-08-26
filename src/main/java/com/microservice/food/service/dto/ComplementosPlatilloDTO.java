package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.ComplementosPlatillo} entity.
 */
public class ComplementosPlatilloDTO implements Serializable {
    
    private Long id;


    private Long complementoId;

    private Long platilloId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplementoId() {
        return complementoId;
    }

    public void setComplementoId(Long complementoId) {
        this.complementoId = complementoId;
    }

    public Long getPlatilloId() {
        return platilloId;
    }

    public void setPlatilloId(Long platilloId) {
        this.platilloId = platilloId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComplementosPlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((ComplementosPlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComplementosPlatilloDTO{" +
            "id=" + getId() +
            ", complementoId=" + getComplementoId() +
            ", platilloId=" + getPlatilloId() +
            "}";
    }
}
