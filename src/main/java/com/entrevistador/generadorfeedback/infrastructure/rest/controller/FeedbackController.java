package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaFeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackCreation feedbackCreation;

    @PostMapping(value = "/solicitudes/entrevistas/{idEntrevista}")
    public Mono<ResponseEntity<String>> crearSolicitudFeedback(
            @PathVariable String idEntrevista,
            @RequestBody List<EntrevistaFeedbackDto> procesoEntrevista) {
        return this.feedbackCreation.iniciarSolicitudFeedback(FeedbackDto.builder()
                        .idEntrevista(idEntrevista)
                        .procesoEntrevista(procesoEntrevista)
                        .build())
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body("Solicitud Feedback generado con exito")));
    }
}
