package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ExtrasPlatillo.
 */
@Entity
@Table(name = "extras_platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExtrasPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "extrasPlatillos", allowSetters = true)
    private Extra extra;

    @ManyToOne
    @JsonIgnoreProperties(value = "extrasPlatillos", allowSetters = true)
    private Platillo platillo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Extra getExtra() {
        return extra;
    }

    public ExtrasPlatillo extra(Extra extra) {
        this.extra = extra;
        return this;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public ExtrasPlatillo platillo(Platillo platillo) {
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
        if (!(o instanceof ExtrasPlatillo)) {
            return false;
        }
        return id != null && id.equals(((ExtrasPlatillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExtrasPlatillo{" +
            "id=" + getId() +
            "}";
    }
}
