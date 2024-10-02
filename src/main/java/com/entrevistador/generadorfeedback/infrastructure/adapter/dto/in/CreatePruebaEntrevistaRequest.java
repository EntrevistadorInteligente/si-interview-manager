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
public class CreatePruebaEntrevistaRequest {
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<CreatePreguntaRequest> preguntas;
    private String rol;
}