package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PruebaEntrevistaResponse {
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<PreguntaResponse> preguntas;
    private String rol;
}