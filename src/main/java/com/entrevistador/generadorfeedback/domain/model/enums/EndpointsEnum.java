package com.entrevistador.generadorfeedback.domain.model.enums;

import lombok.Getter;

@Getter
public enum EndpointsEnum {
    ENVIAR_EVENTO("enviarEvento"),
    FG("FEEDBACK_GENERADAS");

    private String descripcion;

    EndpointsEnum(String descripcion) {
        this.descripcion = descripcion;
    }

}
