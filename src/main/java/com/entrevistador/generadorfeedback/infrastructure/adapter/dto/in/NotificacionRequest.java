package com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in;

import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacionRequest {
    @JsonProperty("tipo")
    private TipoNotificacionEnum tipo;
    @JsonProperty("mensaje")
    private String mensaje;

}
