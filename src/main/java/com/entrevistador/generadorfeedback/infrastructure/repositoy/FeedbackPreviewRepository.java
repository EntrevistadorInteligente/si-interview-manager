package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackPreviewEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FeedbackPreviewRepository extends ReactiveMongoRepository<FeedbackPreviewEntity, String> {
}
