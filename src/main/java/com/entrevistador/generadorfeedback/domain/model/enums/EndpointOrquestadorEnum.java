package com.entrevistador.generadorfeedback.domain.model.enums;

import lombok.Getter;

@Getter
public enum EndpointOrquestadorEnum {
    ENTREVISTA_MUESTRA("entrevistaMuestraId");

    private String descripcion;

    EndpointOrquestadorEnum(String descripcion) {
        this.descripcion = descripcion;
    }

}
