package com.entrevistador.generadorfeedback.infrastructure.rest.router;

import com.entrevistador.generadorfeedback.infrastructure.rest.handler.FeedbackHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FeedbackRouter {
    @Bean
    public RouterFunction<ServerResponse> route(FeedbackHandler handler) {
        return RouterFunctions.route(RequestPredicates.POST("/test")
                .and(RequestPredicates.contentType(MediaType.TEXT_EVENT_STREAM)), handler::createFeedback);
    }
}
