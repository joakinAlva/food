package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Complemento.
 */
@Entity
@Table(name = "complemento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Complemento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complemento")
    private String complemento;

    @OneToMany(mappedBy = "complemento")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ComplementosPlatillo> complementosPlatillos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplemento() {
        return complemento;
    }

    public Complemento complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Set<ComplementosPlatillo> getComplementosPlatillos() {
        return complementosPlatillos;
    }

    public Complemento complementosPlatillos(Set<ComplementosPlatillo> complementosPlatillos) {
        this.complementosPlatillos = complementosPlatillos;
        return this;
    }

    public Complemento addComplementosPlatillos(ComplementosPlatillo complementosPlatillo) {
        this.complementosPlatillos.add(complementosPlatillo);
        complementosPlatillo.setComplemento(this);
        return this;
    }

    public Complemento removeComplementosPlatillos(ComplementosPlatillo complementosPlatillo) {
        this.complementosPlatillos.remove(complementosPlatillo);
        complementosPlatillo.setComplemento(null);
        return this;
    }

    public void setComplementosPlatillos(Set<ComplementosPlatillo> complementosPlatillos) {
        this.complementosPlatillos = complementosPlatillos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Complemento)) {
            return false;
        }
        return id != null && id.equals(((Complemento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Complemento{" +
            "id=" + getId() +
            ", complemento='" + getComplemento() + "'" +
            "}";
    }
}
