package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.InterviewTestService;
import com.entrevistador.generadorfeedback.domain.model.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/v1/muestra")
@RequiredArgsConstructor
public class EntrevistaPruebaController {
    private final InterviewTestService interviewTestService;

    @GetMapping(path = "/preguntas")
    public Flux<SoloPreguntaImp> obtenerPreguntas(@RequestParam("perfil") String perfil){
        return this.interviewTestService.getPreguntas(perfil,3);
    }

    @GetMapping(value = "/obtenerId")
    public Mono<IdEntrevista> obtenerId(){
        return this.interviewTestService.getIdEntrevista("Software%20Engineer%20Java");
    }
}
