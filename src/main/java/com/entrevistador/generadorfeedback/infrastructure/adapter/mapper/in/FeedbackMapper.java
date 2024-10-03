package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ListEntrevistaFeedbackReponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FeedbackMapper {

    // Map Out
    ListEntrevistaFeedbackReponse mapOutFeedbackResponseToFeedbackResponseDto(EntrevistaFeedback entrevistaFeedback);
    // End Map Out

}
