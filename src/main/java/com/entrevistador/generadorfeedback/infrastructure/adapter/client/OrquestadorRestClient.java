package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.domain.model.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.model.enums.EndpointOrquestadorEnum;
import com.entrevistador.generadorfeedback.domain.port.client.OrquestadorClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrquestadorRestClient implements OrquestadorClient {

    private final WebClient webClient;
    private final WebFluxProperties webFluxProperties;

    public OrquestadorRestClient(WebClient.Builder webClientBuilder, WebFluxProperties webFluxProperties) {
        this.webClient = webClientBuilder.baseUrl(webFluxProperties.getWebFluxOrquestador().getUrlBase()).build();
        this.webFluxProperties = webFluxProperties;
    }

    @Override
    public Mono<IdEntrevista> getIdEntrevista(String perfil){
        return this.webClient.get()
                .uri(webFluxProperties.getWebFluxOrquestador()
                        .getEndpoints().get(EndpointOrquestadorEnum.ENTREVISTA_MUESTRA.getDescripcion())
                        .concat(perfil))
                .retrieve()
                .bodyToMono(IdEntrevista.class);
    }
}
