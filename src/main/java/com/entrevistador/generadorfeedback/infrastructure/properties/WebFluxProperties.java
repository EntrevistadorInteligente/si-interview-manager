package com.entrevistador.generadorfeedback.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "feedbackconst")
public class WebFluxProperties {

    private WebfluxComponent webFluxOrquestador;
    private WebfluxComponent webFluxFeedbackPregunta;
    private WebfluxComponent webFluxNotificaciones;
    private int limitPreguntas;

    @Getter
    @Setter
    public static class WebfluxComponent {
        private String urlBase;
        private Map<String, String> endpoints;
    }
}
