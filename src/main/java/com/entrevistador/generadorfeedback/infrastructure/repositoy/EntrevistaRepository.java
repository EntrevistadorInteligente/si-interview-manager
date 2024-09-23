package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EntrevistaRepository extends ReactiveMongoRepository<EntrevistaEntity, String> {
    Flux<EntrevistaEntity> findAllByRol(String rol);
}