package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.RespuestaComentarioRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RespuestaMapper {

    // Map In
    @Mapping(target = "idEntrevista", source = "idEntrevista")
    @Mapping(target = "procesoEntrevista", source = "procesoEntrevista")
    Feedback mapInIdEntrevistaAndprocesoEntrevistaToRespuesta(String idEntrevista, List<RespuestaComentarioRequest> procesoEntrevista);
    // End Map In
}
