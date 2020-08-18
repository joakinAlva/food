package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PlatillosCombo.
 */
@Entity
@Table(name = "platillos_combo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PlatillosCombo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JsonIgnoreProperties(value = "platillosCombos", allowSetters = true)
    private Platillo platillo;

    @ManyToOne
    @JsonIgnoreProperties(value = "platillosCombos", allowSetters = true)
    private OpcionCombo opcionCombo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public PlatillosCombo precio(Double precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public PlatillosCombo platillo(Platillo platillo) {
        this.platillo = platillo;
        return this;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    public OpcionCombo getOpcionCombo() {
        return opcionCombo;
    }

    public PlatillosCombo opcionCombo(OpcionCombo opcionCombo) {
        this.opcionCombo = opcionCombo;
        return this;
    }

    public void setOpcionCombo(OpcionCombo opcionCombo) {
        this.opcionCombo = opcionCombo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlatillosCombo)) {
            return false;
        }
        return id != null && id.equals(((PlatillosCombo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlatillosCombo{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            "}";
    }
}
