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
@Table(name = "modalidad")
@Setter
@Getter
public class Modalidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_modalidad;
    private String nombre_modalidad;
    private String estado_modalidad;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modalidad", fetch = FetchType.EAGER)
    private List<Contratacion> contratacion;

    // Tabla Tipo Modalidad
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_modalidad")
    private TipoModalidad tipoModalidad;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modalidad", fetch = FetchType.EAGER)
    private List<Modificatorio> modificatorio;

}
