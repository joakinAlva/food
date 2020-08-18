package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "platillo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Platillo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "foto_src")
    private String fotoSrc;

    @Column(name = "horario")
    private String horario;

    @Column(name = "precio")
    private Long precio;

    @OneToMany(mappedBy = "platillo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PlatillosCombo> platillosCombos = new HashSet<>();

    @OneToMany(mappedBy = "platillo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ExtrasPlatillo> extrasPlatillos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "platillos", allowSetters = true)
    private Restaurante restaurante;

    @ManyToOne
    @JsonIgnoreProperties(value = "platillos", allowSetters = true)
    private CategoriaPlatillo categoriaPlatillo;

    @ManyToOne
    @JsonIgnoreProperties(value = "platillos", allowSetters = true)
    private TipoPlatillo tipoPlatillo;

    @ManyToMany(mappedBy = "platillos")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<MenuRestaurantePlatillo> menuRestaurantePlatillos = new HashSet<>();

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

    public Platillo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Platillo descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoSrc() {
        return fotoSrc;
    }

    public Platillo fotoSrc(String fotoSrc) {
        this.fotoSrc = fotoSrc;
        return this;
    }

    public void setFotoSrc(String fotoSrc) {
        this.fotoSrc = fotoSrc;
    }

    public String getHorario() {
        return horario;
    }

    public Platillo horario(String horario) {
        this.horario = horario;
        return this;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getPrecio() {
        return precio;
    }

    public Platillo precio(Long precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Set<PlatillosCombo> getPlatillosCombos() {
        return platillosCombos;
    }

    public Platillo platillosCombos(Set<PlatillosCombo> platillosCombos) {
        this.platillosCombos = platillosCombos;
        return this;
    }

    public Platillo addPlatillosCombo(PlatillosCombo platillosCombo) {
        this.platillosCombos.add(platillosCombo);
        platillosCombo.setPlatillo(this);
        return this;
    }

    public Platillo removePlatillosCombo(PlatillosCombo platillosCombo) {
        this.platillosCombos.remove(platillosCombo);
        platillosCombo.setPlatillo(null);
        return this;
    }

    public void setPlatillosCombos(Set<PlatillosCombo> platillosCombos) {
        this.platillosCombos = platillosCombos;
    }

    public Set<ExtrasPlatillo> getExtrasPlatillos() {
        return extrasPlatillos;
    }

    public Platillo extrasPlatillos(Set<ExtrasPlatillo> extrasPlatillos) {
        this.extrasPlatillos = extrasPlatillos;
        return this;
    }

    public Platillo addExtrasPlatillos(ExtrasPlatillo extrasPlatillo) {
        this.extrasPlatillos.add(extrasPlatillo);
        extrasPlatillo.setPlatillo(this);
        return this;
    }

    public Platillo removeExtrasPlatillos(ExtrasPlatillo extrasPlatillo) {
        this.extrasPlatillos.remove(extrasPlatillo);
        extrasPlatillo.setPlatillo(null);
        return this;
    }

    public void setExtrasPlatillos(Set<ExtrasPlatillo> extrasPlatillos) {
        this.extrasPlatillos = extrasPlatillos;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Platillo restaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
        return this;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public CategoriaPlatillo getCategoriaPlatillo() {
        return categoriaPlatillo;
    }

    public Platillo categoriaPlatillo(CategoriaPlatillo categoriaPlatillo) {
        this.categoriaPlatillo = categoriaPlatillo;
        return this;
    }

    public void setCategoriaPlatillo(CategoriaPlatillo categoriaPlatillo) {
        this.categoriaPlatillo = categoriaPlatillo;
    }

    public TipoPlatillo getTipoPlatillo() {
        return tipoPlatillo;
    }

    public Platillo tipoPlatillo(TipoPlatillo tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
        return this;
    }

    public void setTipoPlatillo(TipoPlatillo tipoPlatillo) {
        this.tipoPlatillo = tipoPlatillo;
    }

    public Set<MenuRestaurantePlatillo> getMenuRestaurantePlatillos() {
        return menuRestaurantePlatillos;
    }

    public Platillo menuRestaurantePlatillos(Set<MenuRestaurantePlatillo> menuRestaurantePlatillos) {
        this.menuRestaurantePlatillos = menuRestaurantePlatillos;
        return this;
    }

    public Platillo addMenuRestaurantePlatillo(MenuRestaurantePlatillo menuRestaurantePlatillo) {
        this.menuRestaurantePlatillos.add(menuRestaurantePlatillo);
        menuRestaurantePlatillo.getPlatillos().add(this);
        return this;
    }

    public Platillo removeMenuRestaurantePlatillo(MenuRestaurantePlatillo menuRestaurantePlatillo) {
        this.menuRestaurantePlatillos.remove(menuRestaurantePlatillo);
        menuRestaurantePlatillo.getPlatillos().remove(this);
        return this;
    }

    public void setMenuRestaurantePlatillos(Set<MenuRestaurantePlatillo> menuRestaurantePlatillos) {
        this.menuRestaurantePlatillos = menuRestaurantePlatillos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Platillo)) {
            return false;
        }
        return id != null && id.equals(((Platillo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Platillo{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", fotoSrc='" + getFotoSrc() + "'" +
            ", horario='" + getHorario() + "'" +
            ", precio=" + getPrecio() +
            "}";
    }
}
