package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrquestadorRestClientTest {
    private static MockWebServer mockWebServer;

    private OrquestadorRestClient orquestadorRestClient;

    private final WebFluxProperties webFluxProperties = new WebFluxProperties();
    private final ObjectMapper objectMapper = new ObjectMapper();


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
            put("entrevistaMuestraId", "/v1/entrevistador/public/entrevista_muestra_id?perfil=");
        }};
        webfluxComponent.setEndpoints(endpoints);
        this.webFluxProperties.setWebFluxOrquestador(webfluxComponent);

        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        webfluxComponent.setUrlBase(baseUrl);
        this.orquestadorRestClient =
                new OrquestadorRestClient(WebClient.builder(), this.webFluxProperties);
    }

    @Test
    void testGetIdEntrevista() throws InterruptedException, JsonProcessingException {
        IdEntrevista idEntrevista = IdEntrevista.builder().id("id").build();

        String jsonResponse = this.objectMapper.writeValueAsString(idEntrevista);

        MockResponse mockResponse = new MockResponse()
                .setBody(jsonResponse)
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json");
        mockWebServer.enqueue(mockResponse);

        StepVerifier
                .create(this.orquestadorRestClient.getIdEntrevista("perfil"))
                .assertNext(response -> assertEquals(idEntrevista.getId(), response.getId()))
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/v1/entrevistador/public/entrevista_muestra_id?perfil=perfil", recordedRequest.getPath());
    }

}