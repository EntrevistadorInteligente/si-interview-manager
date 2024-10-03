package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.KafkaPythonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface KafkaPublisherMapper {

    KafkaPythonRequest mapInFeedbackToKafkaPythonRequest(Feedback feedback);

}
