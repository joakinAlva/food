package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A OpcionCombo.
 */
@Entity
@Table(name = "opcion_combo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpcionCombo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opcion_combo")
    private String opcionCombo;

    @Column(name = "cnatidad")
    private String cnatidad;

    @OneToMany(mappedBy = "opcionCombo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PlatillosCombo> platillosCombos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcionCombo() {
        return opcionCombo;
    }

    public OpcionCombo opcionCombo(String opcionCombo) {
        this.opcionCombo = opcionCombo;
        return this;
    }

    public void setOpcionCombo(String opcionCombo) {
        this.opcionCombo = opcionCombo;
    }

    public String getCnatidad() {
        return cnatidad;
    }

    public OpcionCombo cnatidad(String cnatidad) {
        this.cnatidad = cnatidad;
        return this;
    }

    public void setCnatidad(String cnatidad) {
        this.cnatidad = cnatidad;
    }

    public Set<PlatillosCombo> getPlatillosCombos() {
        return platillosCombos;
    }

    public OpcionCombo platillosCombos(Set<PlatillosCombo> platillosCombos) {
        this.platillosCombos = platillosCombos;
        return this;
    }

    public OpcionCombo addPlatillosCombo(PlatillosCombo platillosCombo) {
        this.platillosCombos.add(platillosCombo);
        platillosCombo.setOpcionCombo(this);
        return this;
    }

    public OpcionCombo removePlatillosCombo(PlatillosCombo platillosCombo) {
        this.platillosCombos.remove(platillosCombo);
        platillosCombo.setOpcionCombo(null);
        return this;
    }

    public void setPlatillosCombos(Set<PlatillosCombo> platillosCombos) {
        this.platillosCombos = platillosCombos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcionCombo)) {
            return false;
        }
        return id != null && id.equals(((OpcionCombo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcionCombo{" +
            "id=" + getId() +
            ", opcionCombo='" + getOpcionCombo() + "'" +
            ", cnatidad='" + getCnatidad() + "'" +
            "}";
    }
}
