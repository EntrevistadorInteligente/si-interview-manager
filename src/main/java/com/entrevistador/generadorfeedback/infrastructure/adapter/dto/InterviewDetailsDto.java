package com.entrevistador.generadorfeedback.infrastructure.adapter.dto;

import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDetailsDto {
    private TipoNotificacionEnum interviewProcess;
    private List<RespuestaComentarioDto> procesoEntrevista;
}
