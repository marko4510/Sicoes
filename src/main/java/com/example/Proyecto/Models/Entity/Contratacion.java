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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contratacion")
@Setter
@Getter
public class Contratacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contratacion;
    private String codigo_contratacion;
    private String cargo_servicio_contratacion;
    private Double monto_mensual_contratacion;
    private Double monto_anual_contratacion;
    private String dias_calendario_contratacion;
    private String partida_contratacion;
    private String nro_fojas_contratacion;
    private String gestion_contratacion;
    private String modificatorio_contratacion;
    private String nro_modificacion_contratacion;
    private String boleta_seriedad_contratacion;
    private String boleta_seguridad_contratacion;
    private String boleta_cumplimiento_contratacion;
    private String observacion_contratacion;

    private Date fecha_adjudicado_contratacion;
    private Date fecha_inicio_contratacion;
    private Date fecha_fin_contratacion;


    private String estado_contratacion;

    
    // Tabla Proyecto
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    // Tabla Modalidad
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_modalidad")
    private Modalidad modalidad;

    // Tabla Tipo Modalidad
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_modalidad")
    private TipoModalidad tipoModalidad;

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

    // Tabla Formulario
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_formulario")
    private Formulario formulario;

}
