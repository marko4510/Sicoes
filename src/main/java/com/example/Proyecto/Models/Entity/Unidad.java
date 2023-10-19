package com.example.Proyecto.Models.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
