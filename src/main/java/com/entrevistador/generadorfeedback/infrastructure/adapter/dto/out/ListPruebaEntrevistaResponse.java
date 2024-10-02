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
public class ListPruebaEntrevistaResponse {
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<ListPreguntaResponse> preguntas;
    private String rol;
}