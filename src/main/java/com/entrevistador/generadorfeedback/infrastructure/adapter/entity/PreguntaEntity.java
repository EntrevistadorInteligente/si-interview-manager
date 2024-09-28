package com.entrevistador.generadorfeedback.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreguntaEntity {
    private String descripcion;
    private String titulo;
}