package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface FeedbackRepository extends ReactiveMongoRepository<FeedbackEntity, String> {
    Mono<FeedbackEntity> findByIdEntrevista(String idEntrevista);
}
