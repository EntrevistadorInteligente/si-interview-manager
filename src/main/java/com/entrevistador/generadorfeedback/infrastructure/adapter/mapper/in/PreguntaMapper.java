package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ListPreguntaComentarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PreguntaMapper {
    ListPreguntaComentarioResponse mapOutEntrevistaFeedbackToPreguntaComentarioResponse(EntrevistaFeedback entrevistaFeedback);
}
