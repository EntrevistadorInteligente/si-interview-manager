package com.entrevistador.generadorfeedback.infrastructure.adapter.dto;

import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.CreateRespuestaComentarioRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// TODO: Esta Clase se mantendra por usar la Clase RespuestaComentarioDto, se debe eliminar si no se usa
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDto {
    private String idEntrevista;
    private String username;
    private List<CreateRespuestaComentarioRequest> procesoEntrevista;
}