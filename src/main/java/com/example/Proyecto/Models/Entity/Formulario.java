package com.example.Proyecto.Models.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
