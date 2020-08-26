package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ComplementosPlatillo.
 */
@Entity
@Table(name = "complementos_platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ComplementosPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "complementosPlatillos", allowSetters = true)
    private Complemento complemento;

    @ManyToOne
    @JsonIgnoreProperties(value = "complementosPlatillos", allowSetters = true)
    private Platillo platillo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Complemento getComplemento() {
        return complemento;
    }

    public ComplementosPlatillo complemento(Complemento complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(Complemento complemento) {
        this.complemento = complemento;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public ComplementosPlatillo platillo(Platillo platillo) {
        this.platillo = platillo;
        return this;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComplementosPlatillo)) {
            return false;
        }
        return id != null && id.equals(((ComplementosPlatillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComplementosPlatillo{" +
            "id=" + getId() +
            "}";
    }
}
