package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.service.FeedbackConstruccionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class FeedbackJms {
    private final FeedbackConstruccionService feedbackConstruccionService;

    @KafkaListener(topics = "feedbackListenerTopic", groupId = "my-group")
    public void receptorFeedBack(String jsonData) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            //TODO: Necesito el payload
            FeedbackDto json = mapper.readValue(jsonData, FeedbackDto.class);
            Mono.just(json)
                    .flatMap(this.feedbackConstruccionService::createFeedback)
                    .block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
