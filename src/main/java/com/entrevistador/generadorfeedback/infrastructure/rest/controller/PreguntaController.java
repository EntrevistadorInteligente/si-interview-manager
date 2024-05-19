package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/preguntas")
@RequiredArgsConstructor
public class PreguntaController {
    private final PreguntaCreation preguntaCreation;


    @GetMapping(value = "/entrevistas/{entrevistaId}")
    public Flux<PreguntaComentarioDto> obtenerPreguntas(
            @PathVariable String entrevistaId) {
        return this.preguntaCreation.obtenerPreguntas(entrevistaId);
    }
}
