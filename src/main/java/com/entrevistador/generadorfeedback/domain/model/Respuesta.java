package com.entrevistador.generadorfeedback.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {
    private String idEntrevista;
    private String username;
    private List<RespuestaComentario> procesoEntrevista;
}