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
public class RespuestaDto {
    private String idEntrevista;
    private String username;
    private List<RespuestaComentarioDto> procesoEntrevista;
}