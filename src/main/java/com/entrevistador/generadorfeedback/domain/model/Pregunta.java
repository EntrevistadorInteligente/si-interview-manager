package com.entrevistador.generadorfeedback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Clase que representa el dominio de PreguntaEntity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {
    private String descripcion;
    private String titulo;
}