package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.CreateRespuestaComentarioRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.ConfirmacionResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in.RespuestaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/respuestas")
@RequiredArgsConstructor
public class RespuestaController {
    private final RespuestaCreation respuestaCreation;
    private final RespuestaMapper respuestaMapper;

    @PostMapping(value = "/solicitudes-feedback/entrevistas/{idEntrevista}")
    public Mono<ResponseEntity<ConfirmacionResponse>> crearSolicitudFeedback(
            @PathVariable String idEntrevista,
            @RequestBody List<CreateRespuestaComentarioRequest> procesoEntrevistaRequest
    ) {
        return Mono.just(this.respuestaMapper.mapInIdEntrevistaAndprocesoEntrevistaToRespuesta(idEntrevista, procesoEntrevistaRequest))
                .flatMap(this.respuestaCreation::iniciarSolicitudFeedback)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body(ConfirmacionResponse.builder().valor("Solicitud Feedback generado con exito").build())));
    }
}
