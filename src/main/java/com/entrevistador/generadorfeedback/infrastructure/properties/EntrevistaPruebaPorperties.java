package com.entrevistador.generadorfeedback.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "entrevistapruebaconst")
public class EntrevistaPruebaPorperties {

    private WebfluxComponent webfluxentrevistamuestra;
    private int limitpreguntas;

    @Getter
    @Setter
    public static class WebfluxComponent {
        private String urlbase;
        private String endpoint;
    }
}
