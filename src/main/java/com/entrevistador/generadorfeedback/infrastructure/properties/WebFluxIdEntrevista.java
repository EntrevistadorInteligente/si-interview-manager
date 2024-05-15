package com.entrevistador.generadorfeedback.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "webflux.entrevistamuestra")
public class WebFluxIdEntrevista {
    private String urlbaseidentrevista;
    private String endpointidentrevista;
}
