package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.domain.excepciones.FeedbackException;
import com.entrevistador.generadorfeedback.domain.model.dto.NotifiacionDto;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import com.entrevistador.generadorfeedback.domain.port.client.NotificacionesClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NotificacionesRestClient implements NotificacionesClient {

    private final WebClient webClient;

    public NotificacionesRestClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8085/api/notificaciones").build();
    }

    public Mono<Void> generarNotificacion(String userId,
                                          TipoNotificacionEnum notificacion,
                                          Object object) {
        return
                Mono.fromCallable(() -> new ObjectMapper().writeValueAsString(object))
                        .flatMap(jsonData ->
                                enviar(userId, NotifiacionDto.builder()
                                        .tipo(notificacion)
                                        .mensaje(jsonData)
                                        .build()
                                )
                                        .onErrorMap(JsonProcessingException.class, e -> {
                                            e.printStackTrace();
                                            return new FeedbackException("Error processing JSON");
                                        }));
    }

    private Mono<Void> enviar(String userId, NotifiacionDto notifiacionDto) {
        return this.webClient.post()
                .uri("/v1/eventos/enviar/".concat(userId))
                .bodyValue(notifiacionDto)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
