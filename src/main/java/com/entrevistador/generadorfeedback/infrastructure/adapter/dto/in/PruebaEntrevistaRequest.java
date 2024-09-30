package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PruebaEntrevistaRequest {
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<PreguntaRequest> preguntas;
    private String rol;
}