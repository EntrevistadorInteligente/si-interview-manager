package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaRequest;
import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaResponse;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.FeedbackMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.EntrevistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Component
@RequiredArgsConstructor
public class PruebaEntrevistaDBDao implements PruebaEntrevistaDao {
    private final EntrevistaRepository entrevistaRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public Flux<PruebaEntrevistaResponse> getPreguntas(String rol) {
        return this.entrevistaRepository.findAllByRol(rol)
                .map(this.feedbackMapper::mapEntrevistaEntityToPruebaEntrevistaResponse);
    }

    @Override
    public Mono<PruebaEntrevistaResponse> guardarEntrevista(PruebaEntrevistaRequest request) {
        EntrevistaEntity entity = this.feedbackMapper.mapPruebaEntrevistaRequestToEntrevistaEntity(request);
        return this.entrevistaRepository.save(entity)
                .map(this.feedbackMapper::mapEntrevistaEntityToPruebaEntrevistaResponse);
    }
}
