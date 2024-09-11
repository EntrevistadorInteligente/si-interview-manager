package com.entrevistador.generadorfeedback.infrastructure.adapter.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class EntrevistaDetalleDto {
    private String titulo;
    private String descripcion;
    private List<String> preguntas;
}
