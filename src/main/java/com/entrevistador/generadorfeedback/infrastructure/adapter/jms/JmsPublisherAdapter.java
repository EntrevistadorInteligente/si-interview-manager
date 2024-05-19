package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.domain.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.dto.PythonResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestaDto;
import com.entrevistador.generadorfeedback.infrastructure.adapter.converter.ModelConverter;
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
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic-feedback-solicitud-publisher}")
    private String feedbackPublisherTopic;
    private final ModelConverter modelConverter;

    @Override
    public Mono<Void> enviarsolicitudFeedback(RespuestaDto respuestaDto) {
        try {
            PythonResponseDto pythonResponseDto = modelConverter.convertToDto(respuestaDto, PythonResponseDto.class);

            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(feedbackPublisherTopic,
                    pythonResponseDto
            );
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + respuestaDto.getIdEntrevista() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            respuestaDto.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }

        return Mono.empty();
    }
}
