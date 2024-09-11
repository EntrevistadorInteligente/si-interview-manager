package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.FiltroEntrevista;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.FiltroEntrevistasResponseDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.PreguntaComentarioDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/preguntas")
@RequiredArgsConstructor
public class PreguntaController {
    private final PreguntaCreation preguntaCreation;
    private final FiltroEntrevista filtrarEntrevistas;
    private final FeedbackMapper feedbackMapper;


    @GetMapping(value = "/entrevistas/{entrevistaId}")
    public Flux<PreguntaComentarioDto> obtenerPreguntas(@PathVariable String entrevistaId) {
        return this.preguntaCreation.obtenerPreguntas(entrevistaId)
                .map(this.feedbackMapper::mapPreguntaComentarioEntrevistaToPreguntaComentarioDto);
    }

    @GetMapping
    public Flux<FiltroEntrevistasResponseDto> filtrarEntrevistas(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "seniority") String seniority
     ) {
        return this.filtrarEntrevistas.filtrarEntrevistas(type, seniority);
    }

}
