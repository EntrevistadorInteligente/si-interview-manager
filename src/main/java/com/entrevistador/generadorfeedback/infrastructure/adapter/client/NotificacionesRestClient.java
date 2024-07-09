package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.domain.model.dto.NotifiacionDto;
import com.entrevistador.generadorfeedback.domain.model.enums.EndpointNotificacionesEnum;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class NotificacionesRestClient implements NotificacionesClient {

    private final WebClient webClient;
    private final WebFluxProperties webFluxProperties;
    public NotificacionesRestClient(WebClient.Builder webClientBuilder, WebFluxProperties webFluxProperties) {
        this.webClient = webClientBuilder.baseUrl(webFluxProperties.getWebFluxNotificaciones().getUrlBase()).build();
        this.webFluxProperties = webFluxProperties;
    }

    @Override
    public Mono<Void> enviar(String userId, NotifiacionDto notifiacionDto) {
        return this.webClient.post()
                .uri(webFluxProperties.getWebFluxNotificaciones()
                        .getEndpoints()
                        .get(EndpointNotificacionesEnum.ENVIAR_EVENTO.getDescripcion()).concat(userId))
                .bodyValue(notifiacionDto)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
