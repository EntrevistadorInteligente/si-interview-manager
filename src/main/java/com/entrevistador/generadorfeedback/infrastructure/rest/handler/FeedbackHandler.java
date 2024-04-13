package com.entrevistador.generadorfeedback.infrastructure.rest.handler;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FeedbackHandler {
    private final FeedbackCreation feedbackCreation;

    public Mono<ServerResponse> createFeedback(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(FeedbackDto.class)
                .flatMap(this.feedbackCreation::some)
                .flatMap(feedbackDto -> ServerResponse.ok().bodyValue(feedbackDto));
    }
}
