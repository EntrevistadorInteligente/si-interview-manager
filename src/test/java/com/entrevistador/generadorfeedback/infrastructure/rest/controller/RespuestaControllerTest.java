package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.domain.model.dto.ConfirmacionDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaComentarioDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
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
    private FeedbackMapper feedbackMapper;

    @Test
    void testCrearSolicitudFeedback() {
        RespuestaComentarioDto respuestaComentarioDto = RespuestaComentarioDto.builder().build();

        when(this.feedbackMapper.mapIdEntrevistaAndprocesoEntrevistaToRespuesta(anyString(), anyList())).thenReturn(Respuesta.builder().build());
        when(this.respuestaCreation.iniciarSolicitudFeedback(any())).thenReturn(Mono.empty());

        this.webTestClient
                .post()
                .uri(URL.append("/solicitudes-feedback/entrevistas/{idEntrevista}").toString(), 1)
                .body(Flux.just(respuestaComentarioDto), RespuestaComentarioDto.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(ConfirmacionDto.class)
                .value(ConfirmacionDto::getValor, equalTo("Solicitud Feedback generado con exito"));
    }

}