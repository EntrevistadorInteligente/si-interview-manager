package com.entrevistador.generadorfeedback.domain.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class FeedbackDto {
    private List<String> respuestas;
}
