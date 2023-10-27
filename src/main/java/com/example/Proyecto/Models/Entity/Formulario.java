package com.example.Proyecto.Models.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "formulario")
@Setter
@Getter
public class Formulario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_formulario;
    private String nombre_formulario;
    private String estado_formulario;



    @JsonIgnore
    @ManyToMany(mappedBy = "formulario")
    private Set<Contratacion> contratacion;

}
