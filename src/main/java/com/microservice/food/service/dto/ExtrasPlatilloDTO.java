package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.ExtrasPlatillo} entity.
 */
public class ExtrasPlatilloDTO implements Serializable {
    
    private Long id;


    private Long extraId;

    private Long platilloId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExtraId() {
        return extraId;
    }

    public void setExtraId(Long extraId) {
        this.extraId = extraId;
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
        if (!(o instanceof ExtrasPlatilloDTO)) {
            return false;
        }

        return id != null && id.equals(((ExtrasPlatilloDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExtrasPlatilloDTO{" +
            "id=" + getId() +
            ", extraId=" + getExtraId() +
            ", platilloId=" + getPlatilloId() +
            "}";
    }
}
