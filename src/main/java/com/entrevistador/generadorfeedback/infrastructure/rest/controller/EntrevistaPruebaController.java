package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.PruebaEntrevistaService;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPruebaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntaComentarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/v1/muestra")
@RequiredArgsConstructor
public class EntrevistaPruebaController {
    private final PruebaEntrevistaService interviewTestService;

    @GetMapping(path = "/preguntas")
    public Flux<FeedbackResponseDto> obtenerPreguntas(@RequestParam("perfil") String perfil){
        return this.interviewTestService.getPreguntas(perfil);
    }

//    @PostMapping(path = "/feedback/preguntas")
//    public Flux<FeedbackResponseDto> obtenerRespuestasPreguntas(@RequestBody EntrevistaPruebaDto EntrevistaPreview){
//        System.out.println(EntrevistaPreview.getPerfil());
//        EntrevistaPreview.getProcesoEntrevista().forEach(p -> System.out.println(p.getRespuesta()));
//        return interviewTestService.getEntrevistaFeedback(EntrevistaPreview);
//    }

}
