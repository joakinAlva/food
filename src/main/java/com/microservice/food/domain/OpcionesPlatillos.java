package com.microservice.food.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A OpcionesPlatillos.
 */
@Entity
@Table(name = "opciones_platillos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpcionesPlatillos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion_opcion")
    private String descripcionOpcion;

    @Column(name = "precio")
    private String precio;

    @ManyToOne
    @JsonIgnoreProperties(value = "opcionesPlatillos", allowSetters = true)
    private OpcionPlatillo opcionPlatillo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcionOpcion() {
        return descripcionOpcion;
    }

    public OpcionesPlatillos descripcionOpcion(String descripcionOpcion) {
        this.descripcionOpcion = descripcionOpcion;
        return this;
    }

    public void setDescripcionOpcion(String descripcionOpcion) {
        this.descripcionOpcion = descripcionOpcion;
    }

    public String getPrecio() {
        return precio;
    }

    public OpcionesPlatillos precio(String precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public OpcionPlatillo getOpcionPlatillo() {
        return opcionPlatillo;
    }

    public OpcionesPlatillos opcionPlatillo(OpcionPlatillo opcionPlatillo) {
        this.opcionPlatillo = opcionPlatillo;
        return this;
    }

    public void setOpcionPlatillo(OpcionPlatillo opcionPlatillo) {
        this.opcionPlatillo = opcionPlatillo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcionesPlatillos)) {
            return false;
        }
        return id != null && id.equals(((OpcionesPlatillos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcionesPlatillos{" +
            "id=" + getId() +
            ", descripcionOpcion='" + getDescripcionOpcion() + "'" +
            ", precio='" + getPrecio() + "'" +
            "}";
    }
}
