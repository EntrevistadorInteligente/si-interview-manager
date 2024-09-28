package com.entrevistador.generadorfeedback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PruebaEntrevista {
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<PreguntaRequest> preguntas;
    private String rol;
}