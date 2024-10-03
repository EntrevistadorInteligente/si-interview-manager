package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.KafkaEntrevistaResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.KafkaFeedbackResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface KafkaListenerMapper {

    // Map In
    @Mapping(target = "procesoEntrevista", source = "preguntas")
    Feedback mapInKafkaEntrevistaRequestToFeedback(KafkaEntrevistaResponse kafkaEntrevistaResponse);

    Feedback mapInKafkaFeedbackRequestToFeedback(KafkaFeedbackResponse kafkaFeedbackResponse);
    // End Map In

}
