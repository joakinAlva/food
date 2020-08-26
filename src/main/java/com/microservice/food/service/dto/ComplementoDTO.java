package com.microservice.food.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.microservice.food.domain.Complemento} entity.
 */
public class ComplementoDTO implements Serializable {
    
    private Long id;

    private String complemento;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComplementoDTO)) {
            return false;
        }

        return id != null && id.equals(((ComplementoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComplementoDTO{" +
            "id=" + getId() +
            ", complemento='" + getComplemento() + "'" +
            "}";
    }
}
