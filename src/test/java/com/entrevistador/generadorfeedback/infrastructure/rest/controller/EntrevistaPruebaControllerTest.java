package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.PruebaEntrevistaService;
import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.PruebaEntrevistaResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in.EntrevistaPruebaMapper;
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
    private final StringBuilder URL = new StringBuilder("/v1/demos");

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PruebaEntrevistaService interviewTestService;
    @MockBean
    private EntrevistaPruebaMapper entrevistaPruebaMapper;

    @Test
    void testObtenerPreguntas_Get() {
        when(this.interviewTestService.getPreguntas(anyString())).thenReturn(Flux.just(PruebaEntrevista.builder().build()));
        when(this.entrevistaPruebaMapper.mapOutPruebaEntrevistaToPruebaEntrevistaResponse(any()))
                .thenReturn(PruebaEntrevistaResponse.builder().build());

        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(URL.append("/preguntas").toString())
                        .queryParam("rol", "any")
                        .build())
                .exchange()
                .expectStatus()
                .isOk();
    }
}