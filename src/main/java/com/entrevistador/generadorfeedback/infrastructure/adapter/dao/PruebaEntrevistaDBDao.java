package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out.PruebaEntrevistaMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.EntrevistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PruebaEntrevistaDBDao implements PruebaEntrevistaDao {
    private final EntrevistaRepository entrevistaRepository;
    private final PruebaEntrevistaMapper pruebaEntrevistaMapper;

    @Override
    public Flux<PruebaEntrevista> getPreguntas(String rol) {
        return this.entrevistaRepository.findAllByRol(rol)
                .map(this.pruebaEntrevistaMapper::mapOutEntrevistaEntityToPruebaEntrevista);
    }

    @Override
    public Mono<PruebaEntrevista> guardarEntrevista(PruebaEntrevista pruebaEntrevista) {
        EntrevistaEntity entity = this.pruebaEntrevistaMapper.mapInPruebaEntrevistaToEntrevistaEntity(pruebaEntrevista);
        return this.entrevistaRepository.save(entity)
                .map(this.pruebaEntrevistaMapper::mapOutEntrevistaEntityToPruebaEntrevista);
    }
}
