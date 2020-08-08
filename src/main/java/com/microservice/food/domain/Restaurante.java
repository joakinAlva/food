package com.microservice.food.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Restaurante.
 */
@Entity
@Table(name = "restaurante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "calificacion")
    private Long calificacion;

    @Column(name = "imagen_fondo_src")
    private String imagenFondoSrc;

    @Column(name = "imagen_perfil_src")
    private String imagenPerfilSrc;

    @OneToMany(mappedBy = "restaurante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Platillo> platillos = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MenuRestaurante> menuRestaurantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Restaurante nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Restaurante descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public Restaurante direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCalificacion() {
        return calificacion;
    }

    public Restaurante calificacion(Long calificacion) {
        this.calificacion = calificacion;
        return this;
    }

    public void setCalificacion(Long calificacion) {
        this.calificacion = calificacion;
    }

    public String getImagenFondoSrc() {
        return imagenFondoSrc;
    }

    public Restaurante imagenFondoSrc(String imagenFondoSrc) {
        this.imagenFondoSrc = imagenFondoSrc;
        return this;
    }

    public void setImagenFondoSrc(String imagenFondoSrc) {
        this.imagenFondoSrc = imagenFondoSrc;
    }

    public String getImagenPerfilSrc() {
        return imagenPerfilSrc;
    }

    public Restaurante imagenPerfilSrc(String imagenPerfilSrc) {
        this.imagenPerfilSrc = imagenPerfilSrc;
        return this;
    }

    public void setImagenPerfilSrc(String imagenPerfilSrc) {
        this.imagenPerfilSrc = imagenPerfilSrc;
    }

    public Set<Platillo> getPlatillos() {
        return platillos;
    }

    public Restaurante platillos(Set<Platillo> platillos) {
        this.platillos = platillos;
        return this;
    }

    public Restaurante addPlatillos(Platillo platillo) {
        this.platillos.add(platillo);
        platillo.setRestaurante(this);
        return this;
    }

    public Restaurante removePlatillos(Platillo platillo) {
        this.platillos.remove(platillo);
        platillo.setRestaurante(null);
        return this;
    }

    public void setPlatillos(Set<Platillo> platillos) {
        this.platillos = platillos;
    }

    public Set<MenuRestaurante> getMenuRestaurantes() {
        return menuRestaurantes;
    }

    public Restaurante menuRestaurantes(Set<MenuRestaurante> menuRestaurantes) {
        this.menuRestaurantes = menuRestaurantes;
        return this;
    }

    public Restaurante addMenuRestaurantes(MenuRestaurante menuRestaurante) {
        this.menuRestaurantes.add(menuRestaurante);
        menuRestaurante.setRestaurante(this);
        return this;
    }

    public Restaurante removeMenuRestaurantes(MenuRestaurante menuRestaurante) {
        this.menuRestaurantes.remove(menuRestaurante);
        menuRestaurante.setRestaurante(null);
        return this;
    }

    public void setMenuRestaurantes(Set<MenuRestaurante> menuRestaurantes) {
        this.menuRestaurantes = menuRestaurantes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurante)) {
            return false;
        }
        return id != null && id.equals(((Restaurante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Restaurante{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", calificacion=" + getCalificacion() +
            ", imagenFondoSrc='" + getImagenFondoSrc() + "'" +
            ", imagenPerfilSrc='" + getImagenPerfilSrc() + "'" +
            "}";
    }
}
