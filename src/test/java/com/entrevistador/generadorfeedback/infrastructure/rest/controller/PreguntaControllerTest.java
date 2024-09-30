package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.PreguntaComentarioResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in.FeedbackMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = {PreguntaController.class})
class PreguntaControllerTest {
    private final StringBuilder URL = new StringBuilder("/v1/preguntas");

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PreguntaCreation preguntaCreation;
    @MockBean
    private FeedbackMapper feedbackMapper;

    @Test
    void obtenerPreguntas() {
        when(this.preguntaCreation.obtenerPreguntas(anyString()))
                .thenReturn(Flux.just(EntrevistaFeedback.builder().build()));
        when(this.feedbackMapper.mapOutEntrevistaFeedbackToPreguntaComentarioResponse(any()))
                .thenReturn(PreguntaComentarioResponse.builder().build());

        this.webTestClient
                .get()
                .uri(URL.append("/entrevistas/{entrevistaId}").toString(), 1)
                .exchange()
                .expectStatus()
                .isOk();
    }
}