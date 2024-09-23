package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.PruebaEntrevistaService;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.PruebaEntrevistaDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/v1/demos")
@RequiredArgsConstructor
public class EntrevistaPruebaController {
    private final PruebaEntrevistaService pruebaEntrevistaService;
    private final FeedbackMapper feedbackMapper;

    @GetMapping(path = "/preguntas")
    public Flux<PruebaEntrevistaDto> obtenerPreguntas(@RequestParam("rol") String rol) {
        return this.pruebaEntrevistaService.getPreguntas(rol)
                .map(this.feedbackMapper::mapPruebaEntrevistaResponseToPruebaEntrevistaDto);
    }

    @PostMapping(path = "/entrevistas")
    public Flux<PruebaEntrevistaDto> guardarEntrevistas(@RequestBody List<PruebaEntrevistaDto> entrevistas) {
        return entrevistas
                .stream().map(this.feedbackMapper::mapPruebaEntrevistaDtoToPruebaEntrevistaRequest)
                .flatMap(this.pruebaEntrevistaService::guardarEntrevista)
                .map(this.feedbackMapper::mapPruebaEntrevistaResponseToPruebaEntrevistaDto);
    }
}
