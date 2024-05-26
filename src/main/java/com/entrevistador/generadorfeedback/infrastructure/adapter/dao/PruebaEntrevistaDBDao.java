package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaFeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackPreviewEntity;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackPreviewRepository;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
@Component
@RequiredArgsConstructor
public class PruebaEntrevistaDBDao implements PruebaEntrevistaDao {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackPreviewRepository feedbackPreviewRepository;
    @Override
    public Flux<FeedbackResponseDto> getPreguntas(String idEntrevista, int limit) {
        return this.feedbackRepository.obtenerPreguntas(idEntrevista,limit);
    }

    @Override
    public void guardarPreguntasFeedback(List<FeedbackResponseDto> feedbackResponseList, String perfil) {
        System.out.println("Prueba de funcionamiento feedback");
        feedbackResponseList.forEach(p -> System.out.println(p.getPregunta()));
        this.feedbackPreviewRepository.save(FeedbackPreviewEntity.builder()
                .perfil(perfil)
                .entrevista(feedbackResponseList.stream().map(s -> EntrevistaFeedbackEntity.builder()
                        .idPregunta(s.getIdPregunta())
                        .pregunta(s.getPregunta())
                        .respuesta(s.getRespuesta())
                        .feedback(s.getFeedback())
                        .build()).toList()).build()).subscribe();
    }
}
