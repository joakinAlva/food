package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MenuRestaurantePlatillo.
 */
@Entity
@Table(name = "menu_restaurante_platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MenuRestaurantePlatillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_platillo")
    private String tipoPlatillo;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "menu_restaurante_platillo_platillos",
               joinColumns = @JoinColumn(name = "menu_restaurante_platillo_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "platillos_id", referencedColumnName = "id"))
    private Set<Platillo> platillos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "menuRestaurantePlatillos", allowSetters = true)
    private MenuRestaurante menuRestaurante;

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

    public MenuRestaurantePlatillo tipoPlatillo(String tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
        return this;
    }

    public void setTipoPlatillo(String tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    public Set<Platillo> getPlatillos() {
        return platillos;
    }

    public MenuRestaurantePlatillo platillos(Set<Platillo> platillos) {
        this.platillos = platillos;
        return this;
    }

    public MenuRestaurantePlatillo addPlatillos(Platillo platillo) {
        this.platillos.add(platillo);
        platillo.getMenuRestaurantePlatillos().add(this);
        return this;
    }

    public MenuRestaurantePlatillo removePlatillos(Platillo platillo) {
        this.platillos.remove(platillo);
        platillo.getMenuRestaurantePlatillos().remove(this);
        return this;
    }

    public void setPlatillos(Set<Platillo> platillos) {
        this.platillos = platillos;
    }

    public MenuRestaurante getMenuRestaurante() {
        return menuRestaurante;
    }

    public MenuRestaurantePlatillo menuRestaurante(MenuRestaurante menuRestaurante) {
        this.menuRestaurante = menuRestaurante;
        return this;
    }

    public void setMenuRestaurante(MenuRestaurante menuRestaurante) {
        this.menuRestaurante = menuRestaurante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuRestaurantePlatillo)) {
            return false;
        }
        return id != null && id.equals(((MenuRestaurantePlatillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuRestaurantePlatillo{" +
            "id=" + getId() +
            ", tipoPlatillo='" + getTipoPlatillo() + "'" +
            "}";
    }
}
