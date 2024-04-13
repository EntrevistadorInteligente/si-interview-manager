package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.PreguntasDto;
import com.entrevistador.generadorfeedback.domain.service.FeedbackConstruccionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class JmsListenerAdapter {
    private final FeedbackCreation feedbackCreation;

    @KafkaListener(topics = "feedbackListenerTopic", groupId = "my-group")
    public void receptorFeedBack(String jsonData) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            PreguntasDto json = mapper.readValue(jsonData, PreguntasDto.class);

            Mono.just(FeedbackDto.builder()
                            .idEntrevista(json.getIdEntrevista())
                            .procesoEntrevista(json.getProcesoEntrevista())
                            .build())
                    .flatMap(this.feedbackCreation::guardarFeedback)
                    .block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "preguntasListenerTopic", groupId = "my-group")
    public void receptorPreguntasEntrevista(String jsonData) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            EntrevistaDto json = mapper.readValue(jsonData, EntrevistaDto.class);
            Mono.just(json)
                    .flatMap(this.feedbackCreation::crearEspacioEntrevista)
                    .block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
