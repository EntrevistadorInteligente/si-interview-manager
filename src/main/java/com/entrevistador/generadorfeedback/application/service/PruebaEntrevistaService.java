package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPruebaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.domain.port.client.OrquestadorClient;
import com.entrevistador.generadorfeedback.domain.port.client.PreparadorEntrevistaClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PruebaEntrevistaService implements PruebaEntrevista {

    private final PruebaEntrevistaDao pruebaEntrevistaDao;
    private final WebFluxProperties webFluxProperties;
    private final OrquestadorClient orquestadorClient;
    private final PreparadorEntrevistaClient preparadorEntrevistaClient;

    @Override
    public Flux<FeedbackResponseDto> getPreguntas(String perfil) {
        //TODO: Cambiar el perfil por el id de la entrevista y remover id quemado
        return pruebaEntrevistaDao.getPreguntas("664d6782125a112f4cb8bef3", webFluxProperties.getLimitPreguntas());
        //return orquestadorClient.getIdEntrevista(perfil).flatMapMany(idEntrevista -> pruebaEntrevistaDao.getPreguntas(idEntrevista.getId(),webFluxProperties.getLimitPreguntas()));
    }

    @Override
    public Flux<FeedbackResponseDto> getEntrevistaFeedback(EntrevistaPruebaDto entrevistaPruebaDto) {
        return preparadorEntrevistaClient.obtenerFeedbackMuestra(entrevistaPruebaDto)
                .collectMap(FeedbackComentarioDto::getIdPregunta, FeedbackComentarioDto::getFeedback) //Paso 1
                .flatMapMany(feedbackMap -> Flux.fromIterable(entrevistaPruebaDto.getProcesoEntrevista()) // Paso 2
                        .map(responseDto -> FeedbackResponseDto.builder()
                                .idPregunta(responseDto.getIdPregunta())
                                .pregunta(responseDto.getPregunta())
                                .respuesta(responseDto.getRespuesta())
                                .feedback(feedbackMap.get(responseDto.getIdPregunta()))
                                .build())
                )
                .collectList() // Colectar la lista de FeedbackResponseDto
                .doOnNext(feedbackResponseList ->
                        pruebaEntrevistaDao.guardarPreguntasFeedback(feedbackResponseList, entrevistaPruebaDto.getPerfil())
                ) // Llamar al método para guardar en el repositorio
                .flatMapMany(Flux::fromIterable);
    }


}
