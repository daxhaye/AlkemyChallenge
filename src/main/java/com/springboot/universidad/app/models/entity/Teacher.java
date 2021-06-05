package com.springboot.universidad.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dni;
    private String name;
    private String lastname;
    private boolean activo;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subjects> materiasDadas;


    public Teacher() {
        this.materiasDadas = new ArrayList<Subjects>();
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public int getDni() {
        return dni;
    }


    public void setDni(int dni) {
        this.dni = dni;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public boolean isActivo() {
        return activo;
    }


    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    public List<Subjects> getMateriasDadas() {
        return materiasDadas;
    }


    public void setMateriasDadas(List<Subjects> materiasDadas) {
        this.materiasDadas = materiasDadas;
    }

    public void addMateriasDadas(Subjects materia) {
        this.materiasDadas.add(materia);
    }


    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
