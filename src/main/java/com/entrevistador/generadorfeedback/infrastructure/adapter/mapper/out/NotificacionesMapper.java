package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out;

import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.NotificacionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificacionesMapper {

    // Map In
    NotificacionRequest mapInNotificacionToNotificacionRequest(Notificacion notificacion);
    // End Map In

}
