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
@Table(name = "tipo_modalidad")
@Setter
@Getter
public class TipoModalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_modalidad;
    private String nombre_tipo_modalidad;
    private String estado_tipo_modalidad;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoModalidad", fetch = FetchType.LAZY)
    private List<Contratacion> contratacion;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoModalidad", fetch = FetchType.LAZY)
    private List<Modalidad> modalidad;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoModalidad", fetch = FetchType.LAZY)
    private List<Modificatorio> modificatorio;

}
