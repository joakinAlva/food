package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Extra.
 */
@Entity
@Table(name = "extra")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Extra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extra")
    private String extra;

    @Column(name = "precio")
    private String precio;

    @OneToMany(mappedBy = "extra")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ExtrasPlatillo> extrasPlatillos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public Extra extra(String extra) {
        this.extra = extra;
        return this;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrecio() {
        return precio;
    }

    public Extra precio(String precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Set<ExtrasPlatillo> getExtrasPlatillos() {
        return extrasPlatillos;
    }

    public Extra extrasPlatillos(Set<ExtrasPlatillo> extrasPlatillos) {
        this.extrasPlatillos = extrasPlatillos;
        return this;
    }

    public Extra addExtrasPlatillos(ExtrasPlatillo extrasPlatillo) {
        this.extrasPlatillos.add(extrasPlatillo);
        extrasPlatillo.setExtra(this);
        return this;
    }

    public Extra removeExtrasPlatillos(ExtrasPlatillo extrasPlatillo) {
        this.extrasPlatillos.remove(extrasPlatillo);
        extrasPlatillo.setExtra(null);
        return this;
    }

    public void setExtrasPlatillos(Set<ExtrasPlatillo> extrasPlatillos) {
        this.extrasPlatillos = extrasPlatillos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Extra)) {
            return false;
        }
        return id != null && id.equals(((Extra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Extra{" +
            "id=" + getId() +
            ", extra='" + getExtra() + "'" +
            ", precio='" + getPrecio() + "'" +
            "}";
    }
}
