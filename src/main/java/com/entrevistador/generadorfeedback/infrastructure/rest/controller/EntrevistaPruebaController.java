package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.service.PruebaEntrevistaService;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.CreatePruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ConfirmacionResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ListPruebaEntrevistaResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in.EntrevistaPruebaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/demos")
@RequiredArgsConstructor
public class EntrevistaPruebaController {
    private final PruebaEntrevistaService pruebaEntrevistaService;
    private final EntrevistaPruebaMapper entrevistaPruebaMapper;

    @GetMapping(path = "/preguntas")
    public Flux<ListPruebaEntrevistaResponse> obtenerPreguntas(@RequestParam("rol") String rol) {
        return this.pruebaEntrevistaService.getPreguntas(rol)
                .map(this.entrevistaPruebaMapper::mapOutPruebaEntrevistaToListPruebaEntrevistaResponse);
    }

    @PostMapping(path = "/entrevistas")
    public Mono<ResponseEntity<ConfirmacionResponse>> guardarEntrevistas(@RequestBody List<CreatePruebaEntrevistaRequest> entrevistas) {
        return Flux.fromIterable(entrevistas).map(this.entrevistaPruebaMapper::mapInCreatePruebaEntrevistaRequestToPruebaEntrevista)
                .flatMap(this.pruebaEntrevistaService::guardarEntrevista)
                .map(this.entrevistaPruebaMapper::mapOutPruebaEntrevistaToCreatePruebaEntrevistaResponse)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body(ConfirmacionResponse.builder().valor("Entrevistas guardadas con éxito").build())));
    }

}
