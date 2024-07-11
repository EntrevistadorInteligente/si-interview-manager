package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.PruebaEntrevistaService;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = {EntrevistaPruebaController.class})
class EntrevistaPruebaControllerTest {
    private final StringBuilder URL = new StringBuilder("/v1/muestra");

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PruebaEntrevistaService interviewTestService;
    @MockBean
    private FeedbackMapper feedbackMapper;

    @Test
    void testObtenerPreguntas_Get() {
        when(this.interviewTestService.getPreguntas(anyString())).thenReturn(Flux.just(FeedbackResponse.builder().build()));
        when(this.feedbackMapper.mapFeedbackResponseToFeedbackResponseDto(any())).thenReturn(FeedbackResponseDto.builder().build());

        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URL.append("/preguntas").toString())
                        .queryParam("perfil", "any")
                        .build())
                .exchange()
                .expectStatus()
                .isOk();
    }
}