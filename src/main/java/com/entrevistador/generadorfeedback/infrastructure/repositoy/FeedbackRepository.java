package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.Feedback;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FeedbackRepository extends ReactiveMongoRepository<Feedback, String> {
}
