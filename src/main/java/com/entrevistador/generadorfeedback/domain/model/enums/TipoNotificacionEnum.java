package com.entrevistador.generadorfeedback.domain.model.enums;

import lombok.Getter;

@Getter
public enum TipoNotificacionEnum {
    PG("PREGUNTAS_GENERADAS"),
    FG("FEEDBACK_GENERADAS");

    private String descripcion;

    TipoNotificacionEnum(String descripcion) {
        this.descripcion = descripcion;
    }

}
