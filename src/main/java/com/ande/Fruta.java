package com.ande;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.QueryHint;
import jakarta.persistence.Table;

@Entity
@Table(name="Fruta")
@NamedQuery(name="Fruta.findAll", query = "SELECT f FROM Fruta f ORDER BY f.nombre")
@Cacheable
public class Fruta {
    
    @Id
    @Column(name="id") 
    @GeneratedValue(strategy =GenerationType.IDENTITY)   
    private int id;

    @Column(name="nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "caja_id")
    private Caja caja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fruta() {
    }

    public Fruta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fruta other = (Fruta) obj;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    

}
