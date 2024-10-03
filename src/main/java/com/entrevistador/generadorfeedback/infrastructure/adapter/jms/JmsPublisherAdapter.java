package com.entrevistador.generadorfeedback.infrastructure.adapter.jms;

import com.entrevistador.generadorfeedback.domain.port.jms.JmsPublisherClient;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.in.KafkaPythonRequest;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out.KafkaPublisherMapper;
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
    private final KafkaPublisherMapper kafkaPublisherMapper;

    @Override
    public Mono<Void> enviarsolicitudFeedback(Feedback feedback) {
        try {
            KafkaPythonRequest kafkaPythonRequest = this.kafkaPublisherMapper.mapInFeedbackToKafkaPythonRequest(feedback);

            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(feedbackPublisherTopic,
                    kafkaPythonRequest
            );
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + feedback.getIdEntrevista() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            feedback.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : " + ex.getMessage());
        }

        return Mono.empty();
    }
}
