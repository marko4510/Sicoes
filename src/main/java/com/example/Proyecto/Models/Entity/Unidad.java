package com.example.Proyecto.Models.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "unidad")
@Setter
@Getter
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_unidad;
    private String nombre_unidad;
    private String sigla_unidad;
    private String estado_unidad;

    //LISTA DE RELACION CON LA ENTIDAD PROYECTO
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidad", fetch = FetchType.EAGER)
    private List<Proyecto> proyectos;
}
