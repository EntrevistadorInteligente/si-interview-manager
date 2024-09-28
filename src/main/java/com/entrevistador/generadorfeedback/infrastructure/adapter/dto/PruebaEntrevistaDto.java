package com.entrevistador.generadorfeedback.infrastructure.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PruebaEntrevistaDto {
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<PreguntaDto> preguntas;
    private String rol;
}