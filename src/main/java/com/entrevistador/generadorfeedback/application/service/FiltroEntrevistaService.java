package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.FiltroEntrevista;
import com.entrevistador.generadorfeedback.domain.port.FeedbackDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.FiltroEntrevistasResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FiltroEntrevistaService implements FiltroEntrevista {

    private final FeedbackDao feedbackDao;

    @Autowired
    public FiltroEntrevistaService(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public Flux<FiltroEntrevistasResponseDto> filtrarEntrevistas(String type, String seniority) {
        return null;
    }
}
