package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.NotificacionRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.EntrevistaFeedbackReponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.PreguntaComentarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FeedbackMapper {

    // Map In
    NotificacionRequest mapInNotificacionToNotificacionRequest(Notificacion notificacion);
    // End Map In

    // Map Out
    EntrevistaFeedbackReponse mapOutFeedbackResponseToFeedbackResponseDto(EntrevistaFeedback entrevistaFeedback);

    PreguntaComentarioResponse mapOutEntrevistaFeedbackToPreguntaComentarioResponse(EntrevistaFeedback entrevistaFeedback);
    // End Map Out

}
