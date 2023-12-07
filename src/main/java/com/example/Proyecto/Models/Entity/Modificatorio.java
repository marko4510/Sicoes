package com.example.Proyecto.Models.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "modificatorio")
@Setter
@Getter
public class Modificatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_modificatorio;
    private String codigo_modificatorio;
    private String cargo_servicio_modificatorio;
    private Double monto_mensual_modificatorio;
    private Double monto_anual_modificatorio;
    private String dias_calendario_modificatorio;
    private String gestion_modificatorio;
    private String nro_modificacion_contratacion;
    private String partida_modificatorio;
    private String estado_modificatorio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_adjudicado_modificatorio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_inicio_modificatorio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_fin_modificatorio;

    // Tabla Modalidad
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modalidad")
    private Modalidad modalidad;

    // Tabla Persona
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    // Tabla Grado
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grado")
    private Grado grado;

    // Tabla Proyecto
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    // Tabla Archivo Adjunto
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_archivo_adjunto")
    private ArchivoAdjunto archivoAdjunto;

    // Tabla Contratación
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_contratacion")
    private Contratacion contratacion;
}
