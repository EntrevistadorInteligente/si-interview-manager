package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.domain.model.dto.PythonResponseDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public final class JmsPublisherAdapter implements JmsPublisherClient {
    @Value("${kafka.topic-feedback-solicitud-publisher}")
    private String feedbackPublisherTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final FeedbackMapper feedbackMapper;

    @Override
    public Mono<Void> enviarsolicitudFeedback(Respuesta respuesta) {
        try {
            PythonResponseDto pythonResponseDto = this.feedbackMapper.mapRespuestaToPythonResponseDto(respuesta);

            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(feedbackPublisherTopic,
                    pythonResponseDto
            );
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + respuesta.getIdEntrevista() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            respuesta.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }

        return Mono.empty();
    }
}
