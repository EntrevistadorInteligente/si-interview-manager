package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.PruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.PruebaEntrevistaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntrevistaPruebaMapper {

    // Map In
    PruebaEntrevista mapInPruebaEntrevistaRequestToPruebaEntrevista(PruebaEntrevistaRequest pruebaEntrevistaRequest);
    // End Map In

    // Map Out
    PruebaEntrevistaResponse mapOutPruebaEntrevistaToPruebaEntrevistaResponse(PruebaEntrevista feedbackResponse);
    // End Map Out

}
