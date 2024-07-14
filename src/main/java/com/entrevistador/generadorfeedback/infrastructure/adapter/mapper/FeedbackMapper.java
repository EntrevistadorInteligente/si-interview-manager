package com.entrevistador.generadorfeedback.infrastructure.adapter.mapper;

import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackComentario;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.domain.model.Pregunta;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentario;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import com.entrevistador.generadorfeedback.domain.model.PreguntaGenerada;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.domain.model.RespuestaComentario;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.NotificacionDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.PreguntaComentarioDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.PythonResponseDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.RespuestaComentarioDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaFeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {UUID.class})
public interface FeedbackMapper {
    Entrevista mapEntrevistaDtoToEntrevista(EntrevistaDto entrevistaDto);

    PythonResponseDto mapRespuestaToPythonResponseDto(Respuesta respuesta);

    @Mapping(target = "idEntrevista", source = "idEntrevista")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "entrevista", source = "preguntas")
    FeedbackEntity mapEntrevistaToFeedbackEntity(Entrevista entrevista);

    @Mapping(target = "idPregunta", expression = "java(UUID.randomUUID().toString())")
    EntrevistaFeedbackEntity mapPreguntaToEntrevistaFeedbackEntity(PreguntaGenerada preguntaGenerada);

    @Mapping(target = "procesoEntrevista", source = "entrevista")
    Pregunta mapFeedbackEntityToPreguntaSave(FeedbackEntity feedbackEntity);

    @Mapping(target = "pregunta", source = "respuesta")
    PreguntaComentario mapEntrevistaFeedbackEntityToPreguntaComentarioSave(EntrevistaFeedbackEntity entrevistaFeedbackEntity);

    @Mapping(target = "procesoEntrevista", source = "entrevista")
    Respuesta mapFeedbackEntityToRespuesta(FeedbackEntity feedbackEntity);

    RespuestaComentario mapEntrevistaFeedbackEntityToRespuestaComentario(EntrevistaFeedbackEntity entrevistaFeedbackEntity);

    Feedback mapFeedbackDtoToFeedback(FeedbackDto feedbackDto);

    @Mapping(target = "procesoEntrevista", source = "entrevista")
    Feedback mapFeedbackEntityToFeedback(FeedbackEntity feedbackEntity);

    @Mapping(target = "score", ignore = true)
    FeedbackComentario mapEntrevistaFeedbackEntityToFeedbackComentario(EntrevistaFeedbackEntity entrevistaFeedbackEntity);

    PreguntaComentarioEntrevista mapEntrevistaFeedbackEntityToPreguntaComentario(EntrevistaFeedbackEntity entrevistaFeedbackEntity);

    FeedbackResponse mapEntrevistaFeedbackEntityToFeedbackResponse(EntrevistaFeedbackEntity entrevistaFeedbackEntity);

    FeedbackResponseDto mapFeedbackResponseToFeedbackResponseDto(FeedbackResponse feedbackResponse);

    FeedbackResponse mapEntrevistaFeedbackEntityToFeedbackResponseDto(EntrevistaFeedbackEntity entrevistaFeedbackEntity);

    PreguntaComentarioDto mapPreguntaComentarioEntrevistaToPreguntaComentarioDto(PreguntaComentarioEntrevista preguntaComentarioEntrevista);

    @Mapping(target = "idEntrevista", source = "idEntrevista")
    @Mapping(target = "procesoEntrevista", source = "procesoEntrevista")
    Respuesta mapIdEntrevistaAndprocesoEntrevistaToRespuesta(String idEntrevista, List<RespuestaComentarioDto> procesoEntrevista);

    NotificacionDto mapNotificacionToNotificacionDto(Notificacion notificacion);
}
