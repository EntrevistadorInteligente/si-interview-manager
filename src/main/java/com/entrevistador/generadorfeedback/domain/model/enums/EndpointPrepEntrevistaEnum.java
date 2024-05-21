package com.entrevistador.generadorfeedback.domain.model.enums;

import lombok.Getter;

@Getter
public enum EndpointPrepEntrevistaEnum {
    FEEDBACK_PREGUNTA("obtenerFeedbackPreview");

    private String description;

    EndpointPrepEntrevistaEnum(String description) {
        this.description = description;
    }
}
