package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.domain.model.Notificacion;
import com.entrevistador.generadorfeedback.domain.model.enums.EndpointNotificacionesEnum;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class NotificacionesRestClient implements NotificacionesClient {

    private final WebClient webClient;
    private final WebFluxProperties webFluxProperties;
    private final FeedbackMapper feedbackMapper;

    @Autowired
    public NotificacionesRestClient(WebClient.Builder webClientBuilder, WebFluxProperties webFluxProperties, FeedbackMapper feedbackMapper) {
        this.webClient = webClientBuilder.baseUrl(webFluxProperties.getWebFluxNotificaciones().getUrlBase()).build();
        this.webFluxProperties = webFluxProperties;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public Mono<Void> enviar(String userId, Notificacion notificacion) {
        return this.webClient.post()
                .uri(webFluxProperties.getWebFluxNotificaciones()
                        .getEndpoints()
                        .get(EndpointNotificacionesEnum.ENVIAR_EVENTO.getDescripcion()).concat(userId))
                .bodyValue(this.feedbackMapper.mapNotificacionToNotificacionDto(notificacion))
                .retrieve()
                .bodyToMono(Void.class);
    }

}
