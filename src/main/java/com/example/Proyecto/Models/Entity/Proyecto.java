package com.example.Proyecto.Models.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "proyecto")
@Setter
@Getter
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proyecto;
    private String nombre_proyecto;
    private String numero_proyecto;
    private String estado_proyecto;

    // ENTIDAD UNIDAD
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad")
    private Unidad unidad;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto", fetch = FetchType.LAZY)
    private List<Contratacion> contratacion;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto", fetch = FetchType.LAZY)
    private List<Modificatorio> modificatorio;
}
