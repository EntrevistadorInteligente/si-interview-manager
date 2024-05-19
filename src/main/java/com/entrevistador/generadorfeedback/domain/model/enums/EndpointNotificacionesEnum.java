package com.entrevistador.generadorfeedback.domain.model.enums;

import lombok.Getter;

@Getter
public enum EndpointNotificacionesEnum {
    ENVIAR_EVENTO("enviarEvento");

    private String descripcion;

    EndpointNotificacionesEnum(String descripcion) {
        this.descripcion = descripcion;
    }

}
