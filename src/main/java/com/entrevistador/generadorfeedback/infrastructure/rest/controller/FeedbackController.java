package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackCreation feedbackCreation;

    //TODO: Dejar esta forma o cambiar al enfoce Router
    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<?>> some(@RequestBody FeedbackDto request) {
        return feedbackCreation.some(request)
                .map(ResponseEntity::ok);
    }
}
