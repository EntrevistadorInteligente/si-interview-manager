package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.exception.FeedbackProcessStatusException;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.CreateRespuestaComentarioRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ConfirmacionResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in.RespuestaMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@WebFluxTest(controllers = {RespuestaController.class})
class RespuestaControllerTest {
    private final StringBuilder URL = new StringBuilder("/v1/respuestas");

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RespuestaCreation respuestaCreation;
    @MockBean
    private RespuestaMapper respuestaMapper;

    @Test
    void testCrearSolicitudFeedback() {
        CreateRespuestaComentarioRequest createRespuestaComentarioRequest = CreateRespuestaComentarioRequest.builder().build();

        when(this.respuestaMapper.mapInIdEntrevistaAndprocesoEntrevistaToRespuesta(anyString(), anyList())).thenReturn(Feedback.builder().build());
        when(this.respuestaCreation.iniciarSolicitudFeedback(any())).thenReturn(Mono.empty());

        this.webTestClient
                .post()
                .uri(URL.append("/solicitudes-feedback/entrevistas/{idEntrevista}").toString(), 1)
                .body(Flux.just(createRespuestaComentarioRequest), CreateRespuestaComentarioRequest.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(ConfirmacionResponse.class)
                .value(ConfirmacionResponse::getValor, equalTo("Solicitud Feedback generado con exito"));
    }

    @Test
    void testCrearSolicitudFeedback_FeedbackProcessStatusException() {
        CreateRespuestaComentarioRequest createRespuestaComentarioRequest = CreateRespuestaComentarioRequest.builder().build();

        when(this.respuestaMapper.mapInIdEntrevistaAndprocesoEntrevistaToRespuesta(anyString(), anyList())).thenReturn(Feedback.builder().build());
        when(this.respuestaCreation.iniciarSolicitudFeedback(any())).thenReturn(Mono.error(new FeedbackProcessStatusException("error")));

        this.webTestClient
                .post()
                .uri(URL.append("/solicitudes-feedback/entrevistas/{idEntrevista}").toString(), 1)
                .body(Flux.just(createRespuestaComentarioRequest), CreateRespuestaComentarioRequest.class)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class)
                .isEqualTo("error");

    }

}