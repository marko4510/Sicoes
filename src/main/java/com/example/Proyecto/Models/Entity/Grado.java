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
@Table(name = "grado")
@Setter
@Getter
public class Grado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_grado;
    private String nombre_grado;
    private String estado_grado;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grado", fetch = FetchType.EAGER)
    private List<Contratacion> contratacion;

}
