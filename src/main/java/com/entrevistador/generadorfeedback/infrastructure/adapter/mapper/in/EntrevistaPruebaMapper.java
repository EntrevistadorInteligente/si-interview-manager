package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.CreatePruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.CreatePruebaEntrevistaResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ListPruebaEntrevistaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntrevistaPruebaMapper {

    // Map In
    PruebaEntrevista mapInCreatePruebaEntrevistaRequestToPruebaEntrevista(CreatePruebaEntrevistaRequest createPruebaEntrevistaRequest);
    // End Map In

    // Map Out
    ListPruebaEntrevistaResponse mapOutPruebaEntrevistaToListPruebaEntrevistaResponse(PruebaEntrevista feedbackResponse);

    CreatePruebaEntrevistaResponse mapOutPruebaEntrevistaToCreatePruebaEntrevistaResponse(PruebaEntrevista feedbackResponse);
    // End Map Out

}
