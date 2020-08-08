package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CategoriaPlatillo.
 */
@Entity
@Table(name = "categoria_platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CategoriaPlatillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "imagen_src")
    private String imagenSrc;

    @OneToMany(mappedBy = "categoriaPlatillo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Platillo> platillos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public CategoriaPlatillo categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagenSrc() {
        return imagenSrc;
    }

    public CategoriaPlatillo imagenSrc(String imagenSrc) {
        this.imagenSrc = imagenSrc;
        return this;
    }

    public void setImagenSrc(String imagenSrc) {
        this.imagenSrc = imagenSrc;
    }

    public Set<Platillo> getPlatillos() {
        return platillos;
    }

    public CategoriaPlatillo platillos(Set<Platillo> platillos) {
        this.platillos = platillos;
        return this;
    }

    public CategoriaPlatillo addPlatillos(Platillo platillo) {
        this.platillos.add(platillo);
        platillo.setCategoriaPlatillo(this);
        return this;
    }

    public CategoriaPlatillo removePlatillos(Platillo platillo) {
        this.platillos.remove(platillo);
        platillo.setCategoriaPlatillo(null);
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
        if (!(o instanceof CategoriaPlatillo)) {
            return false;
        }
        return id != null && id.equals(((CategoriaPlatillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoriaPlatillo{" +
            "id=" + getId() +
            ", categoria='" + getCategoria() + "'" +
            ", imagenSrc='" + getImagenSrc() + "'" +
            "}";
    }
}
