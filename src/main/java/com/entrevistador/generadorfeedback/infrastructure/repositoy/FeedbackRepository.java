package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.Feedback;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface FeedbackRepository extends ReactiveMongoRepository<Feedback, String> {
    Mono<Feedback> findByIdEntrevista(String idEntrevista);
}
