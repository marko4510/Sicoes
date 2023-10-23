package com.example.Proyecto.Models.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "archivo_adjunto")
@Setter
@Getter
public class ArchivoAdjunto  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruta;
    private String nombre_archivo;
    private String estado_archivo_adjunto;
    private String requerimiento_archivo_adjunto;





}
