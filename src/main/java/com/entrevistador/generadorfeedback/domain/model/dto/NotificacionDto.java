package com.entrevistador.generadorfeedback.domain.model.dto;

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
public class NotificacionDto {
    @JsonProperty("tipo")
    private TipoNotificacionEnum tipo;
    @JsonProperty("mensaje")
    private String mensaje;

}
