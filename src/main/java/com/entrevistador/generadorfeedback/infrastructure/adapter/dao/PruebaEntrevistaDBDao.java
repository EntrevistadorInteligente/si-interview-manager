package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class PruebaEntrevistaDBDao implements PruebaEntrevistaDao {
    private final FeedbackRepository feedbackRepository;
    @Override
    public Flux<SoloPreguntaImp> getPreguntas(String idEntrevista, int limit) {
        return this.feedbackRepository.obtenerPreguntas(idEntrevista,limit);
    }
}
