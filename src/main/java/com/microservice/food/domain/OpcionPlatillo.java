package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OpcionPlatillo.
 */
@Entity
@Table(name = "opcion_platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpcionPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opcion_platillo")
    private String opcionPlatillo;

    @OneToMany(mappedBy = "opcionPlatillo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<OpcionesPlatillos> opcionesPlatillos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcionPlatillo() {
        return opcionPlatillo;
    }

    public OpcionPlatillo opcionPlatillo(String opcionPlatillo) {
        this.opcionPlatillo = opcionPlatillo;
        return this;
    }

    public void setOpcionPlatillo(String opcionPlatillo) {
        this.opcionPlatillo = opcionPlatillo;
    }

    public Set<OpcionesPlatillos> getOpcionesPlatillos() {
        return opcionesPlatillos;
    }

    public OpcionPlatillo opcionesPlatillos(Set<OpcionesPlatillos> opcionesPlatillos) {
        this.opcionesPlatillos = opcionesPlatillos;
        return this;
    }

    public OpcionPlatillo addOpcionesPlatillos(OpcionesPlatillos opcionesPlatillos) {
        this.opcionesPlatillos.add(opcionesPlatillos);
        opcionesPlatillos.setOpcionPlatillo(this);
        return this;
    }

    public OpcionPlatillo removeOpcionesPlatillos(OpcionesPlatillos opcionesPlatillos) {
        this.opcionesPlatillos.remove(opcionesPlatillos);
        opcionesPlatillos.setOpcionPlatillo(null);
        return this;
    }

    public void setOpcionesPlatillos(Set<OpcionesPlatillos> opcionesPlatillos) {
        this.opcionesPlatillos = opcionesPlatillos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcionPlatillo)) {
            return false;
        }
        return id != null && id.equals(((OpcionPlatillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcionPlatillo{" +
            "id=" + getId() +
            ", opcionPlatillo='" + getOpcionPlatillo() + "'" +
            "}";
    }
}
