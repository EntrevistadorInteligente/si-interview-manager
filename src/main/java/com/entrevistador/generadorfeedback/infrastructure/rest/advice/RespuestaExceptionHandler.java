package com.entrevistador.generadorfeedback.infrastructure.rest.advice;

import com.entrevistador.generadorfeedback.domain.exception.FeedbackProcessStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class RespuestaExceptionHandler {
    @ExceptionHandler(value = {FeedbackProcessStatusException.class})
    public Mono<ResponseEntity<String>> feedbackProcessStatusException(FeedbackProcessStatusException exception) {
        return Mono.just(ResponseEntity.badRequest().body(exception.getMessage()));
    }
}
