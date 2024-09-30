package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;
import com.entrevistador.generadorfeedback.application.usescases.PreguntaCreation;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.KafkaEntrevistaResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.out.KafkaFeedbackResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.in.KafkaListenerMapper;
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
    private final KafkaListenerMapper kafkaListenerMapper;

    @KafkaListener(topics = "feedbackListenerTopic", groupId = "my-group2")
    public void receptorFeedBack(String jsonData) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            KafkaFeedbackResponse kafkaFeedbackResponse = mapper.readValue(jsonData, KafkaFeedbackResponse.class);

            Mono.just(kafkaFeedbackResponse)
                    .map(this.kafkaListenerMapper::mapInKafkaFeedbackRequestToFeedback)
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
            KafkaEntrevistaResponse kafkaEntrevistaResponse = mapper.readValue(jsonData, KafkaEntrevistaResponse.class);

            Mono.just(kafkaEntrevistaResponse)
                    .map(this.kafkaListenerMapper::mapInKafkaEntrevistaRequestToFeedback)
                    .flatMap(this.preguntaCreation::guardarPreguntas)
                    .block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
