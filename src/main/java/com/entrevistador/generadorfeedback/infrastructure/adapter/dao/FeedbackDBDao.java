package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackDto;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.Feedback;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FeedbackDBDao implements FeedbackDao {
    private final FeedbackRepository feedbackRepository;

    @Override
    public Mono<FeedbackDto> createFeedback() {
        return this.feedbackRepository.save(Feedback.builder().build())
                .map(feedback -> FeedbackDto.builder().build());
    }
}
