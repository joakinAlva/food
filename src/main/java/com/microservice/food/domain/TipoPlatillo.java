package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoPlatillo.
 */
@Entity
@Table(name = "tipo_platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TipoPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_platillo")
    private String tipoPlatillo;

    @OneToMany(mappedBy = "tipoPlatillo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Platillo> platillos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoPlatillo() {
        return tipoPlatillo;
    }

    public TipoPlatillo tipoPlatillo(String tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
        return this;
    }

    public void setTipoPlatillo(String tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    public Set<Platillo> getPlatillos() {
        return platillos;
    }

    public TipoPlatillo platillos(Set<Platillo> platillos) {
        this.platillos = platillos;
        return this;
    }

    public TipoPlatillo addPlatillos(Platillo platillo) {
        this.platillos.add(platillo);
        platillo.setTipoPlatillo(this);
        return this;
    }

    public TipoPlatillo removePlatillos(Platillo platillo) {
        this.platillos.remove(platillo);
        platillo.setTipoPlatillo(null);
        return this;
    }

    public void setPlatillos(Set<Platillo> platillos) {
        this.platillos = platillos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoPlatillo)) {
            return false;
        }
        return id != null && id.equals(((TipoPlatillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoPlatillo{" +
            "id=" + getId() +
            ", tipoPlatillo='" + getTipoPlatillo() + "'" +
            "}";
    }
}
