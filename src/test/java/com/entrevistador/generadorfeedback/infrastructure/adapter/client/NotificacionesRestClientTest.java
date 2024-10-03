package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out.NotificacionesMapper;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificacionesRestClientTest {
    private static MockWebServer mockWebServer;

    private NotificacionesRestClient notificacionesRestClient;

    private final WebFluxProperties webFluxProperties = new WebFluxProperties();
    private final NotificacionesMapper notificacionesMapper = Mappers.getMapper(NotificacionesMapper.class);

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void init() {
        WebFluxProperties.WebfluxComponent webfluxComponent = new WebFluxProperties.WebfluxComponent();
        HashMap<String, String> endpoints = new HashMap<>() {{
            put("enviarEvento", "/v1/eventos/enviar/");
        }};
        webfluxComponent.setEndpoints(endpoints);
        this.webFluxProperties.setWebFluxNotificaciones(webfluxComponent);

        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        webfluxComponent.setUrlBase(baseUrl);
        this.notificacionesRestClient =
                new NotificacionesRestClient(WebClient.builder(), this.webFluxProperties, this.notificacionesMapper);
    }

    @Test
    void testEnviar() throws InterruptedException {
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200);
        mockWebServer.enqueue(mockResponse);

        StepVerifier
                .create(this.notificacionesRestClient.enviar("123", Notificacion.builder().build()))
                .verifyComplete();

        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/v1/eventos/enviar/123", request.getPath());
        assertEquals("POST", request.getMethod());
    }

}