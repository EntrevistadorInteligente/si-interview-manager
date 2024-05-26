package com.entrevistador.generadorfeedback.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EntrevistaPruebaDto {
    private String perfil;
    private List<FeedbackResponseDto> procesoEntrevista;
}
