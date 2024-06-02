package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class JmsListenerAdapter {
    private final FeedbackCreation feedbackCreation;
    private final PreguntaCreation preguntaCreation;

    @KafkaListener(topics = "feedbackListenerTopic", groupId = "my-group2")
    public void receptorFeedBack(String jsonData) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            FeedbackDto json = mapper.readValue(jsonData, FeedbackDto.class);

            Mono.just(json)
                    .flatMap(this.feedbackCreation::actualizarFeedback)
                    .block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "preguntasListenerTopic", groupId = "my-group2")
    public void receptorPreguntasEntrevista(String jsonData) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            EntrevistaDto json = mapper.readValue(jsonData, EntrevistaDto.class);
            Mono.just(json)
                    .flatMap(this.preguntaCreation::guardarPreguntas)
                    .block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
