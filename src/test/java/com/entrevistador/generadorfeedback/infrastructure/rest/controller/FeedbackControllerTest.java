package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.EntrevistaFeedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.EntrevistaFeedbackReponse;
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

@WebFluxTest(controllers = {FeedbackController.class})
class FeedbackControllerTest {
    private final StringBuilder URL = new StringBuilder("/v1/feedback");

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FeedbackCreation feedbackCreation;
    @MockBean
    private FeedbackMapper feedbackMapper;

    @Test
    void testObtenerFeedback_Get() {
        when(this.feedbackCreation.obtenerFeedback(anyString())).thenReturn(Flux.just(EntrevistaFeedback.builder().build()));
        when(this.feedbackMapper.mapOutFeedbackResponseToFeedbackResponseDto(any())).thenReturn(EntrevistaFeedbackReponse.builder().build());

        this.webTestClient
                .get()
                .uri(URL.append("/entrevistas/{entrevistaId}").toString(), 1)
                .exchange()
                .expectStatus()
                .isOk();
    }
}