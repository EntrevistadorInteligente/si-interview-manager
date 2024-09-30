package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PruebaEntrevistaMapper {

    // Map In
    EntrevistaEntity mapInPruebaEntrevistaToEntrevistaEntity(PruebaEntrevista pruebaEntrevista);
    // End Map In

    // Map Out
    PruebaEntrevista mapOutEntrevistaEntityToPruebaEntrevista(EntrevistaEntity entrevistaEntity);
    // End Map Out

}
