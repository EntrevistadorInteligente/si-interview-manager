package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackCreation feedbackCreation;

    @GetMapping(value = "/entrevistas/{entrevistaId}")
    public Flux<FeedbackResponseDto> obtenerFeedback(
            @PathVariable String entrevistaId) {
        return this.feedbackCreation.obtenerFeedback(entrevistaId);
    }

}
