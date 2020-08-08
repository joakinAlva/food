package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MenuRestaurante.
 */
@Entity
@Table(name = "menu_restaurante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MenuRestaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_restaurante")
    private String menuRestaurante;

    @OneToMany(mappedBy = "menuRestaurante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MenuRestaurantePlatillo> menuRestaurantePlatillos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "menuRestaurantes", allowSetters = true)
    private Restaurante restaurante;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuRestaurante() {
        return menuRestaurante;
    }

    public MenuRestaurante menuRestaurante(String menuRestaurante) {
        this.menuRestaurante = menuRestaurante;
        return this;
    }

    public void setMenuRestaurante(String menuRestaurante) {
        this.menuRestaurante = menuRestaurante;
    }

    public Set<MenuRestaurantePlatillo> getMenuRestaurantePlatillos() {
        return menuRestaurantePlatillos;
    }

    public MenuRestaurante menuRestaurantePlatillos(Set<MenuRestaurantePlatillo> menuRestaurantePlatillos) {
        this.menuRestaurantePlatillos = menuRestaurantePlatillos;
        return this;
    }

    public MenuRestaurante addMenuRestaurantePlatillo(MenuRestaurantePlatillo menuRestaurantePlatillo) {
        this.menuRestaurantePlatillos.add(menuRestaurantePlatillo);
        menuRestaurantePlatillo.setMenuRestaurante(this);
        return this;
    }

    public MenuRestaurante removeMenuRestaurantePlatillo(MenuRestaurantePlatillo menuRestaurantePlatillo) {
        this.menuRestaurantePlatillos.remove(menuRestaurantePlatillo);
        menuRestaurantePlatillo.setMenuRestaurante(null);
        return this;
    }

    public void setMenuRestaurantePlatillos(Set<MenuRestaurantePlatillo> menuRestaurantePlatillos) {
        this.menuRestaurantePlatillos = menuRestaurantePlatillos;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public MenuRestaurante restaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        return this;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuRestaurante)) {
            return false;
        }
        return id != null && id.equals(((MenuRestaurante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuRestaurante{" +
            "id=" + getId() +
            ", menuRestaurante='" + getMenuRestaurante() + "'" +
            "}";
    }
}
