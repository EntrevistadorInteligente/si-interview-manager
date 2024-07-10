package com.entrevistador.generadorfeedback.infrastructure.rest.controller;

import com.entrevistador.generadorfeedback.application.usescases.RespuestaCreation;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.ConfirmacionDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.RespuestaComentarioDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
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
    private final FeedbackMapper feedbackMapper;


    @PostMapping(value = "/solicitudes-feedback/entrevistas/{idEntrevista}")
    public Mono<ResponseEntity<ConfirmacionDto>> crearSolicitudFeedback(
            @PathVariable String idEntrevista,
            @RequestBody List<RespuestaComentarioDto> procesoEntrevistaDto
    ) {
        return Mono.just(this.feedbackMapper.mapIdEntrevistaAndprocesoEntrevistaToRespuesta(idEntrevista, procesoEntrevistaDto))
                .flatMap(this.respuestaCreation::iniciarSolicitudFeedback)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                        .body(ConfirmacionDto.builder().valor("Solicitud Feedback generado con exito").build())));
    }
}
