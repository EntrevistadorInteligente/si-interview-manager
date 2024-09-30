package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out;

import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaFeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {UUID.class})
public interface FeedbackDBMapper {

    // Map In
    @Mapping(target = "idEntrevista", source = "idEntrevista")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "entrevista", source = "procesoEntrevista")
    FeedbackEntity mapInFeedbackToFeedbackEntity(Feedback feedback);

    @Mapping(target = "idPregunta", expression = "java(UUID.randomUUID().toString())")
    EntrevistaFeedbackEntity mapInEntrevistaFeedbackToEntrevistaFeedbackEntity(EntrevistaFeedback entrevistaFeedback);
    // End Map In

    // Map Out
    @Mapping(target = "procesoEntrevista", source = "entrevista")
    Feedback mapOutFeedbackEntityToFeedback(FeedbackEntity feedbackEntity);

    EntrevistaFeedback mapOutEntrevistaFeedbackEntityToEntrevistaFeedback(EntrevistaFeedbackEntity entrevistaFeedbackEntity);
    // End Map Out

}
