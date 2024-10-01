package com.entrevistador.generadorfeedback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 * Clase que representa el dominio de EntrevistaEntity
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PruebaEntrevista {
    private String id;
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<Pregunta> preguntas;
    private String rol;
}