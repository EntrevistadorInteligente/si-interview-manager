package com.entrevistador.generadorfeedback.domain.model;

import com.entrevistador.generadorfeedback.domain.exception.FeedbackProcessStatusException;
import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    private String uuid;
    private String idEntrevista;
    private String username;
    private TipoNotificacionEnum feedbackProcess;
    private List<EntrevistaFeedback> procesoEntrevista;

    public Mono<Feedback> validateFeedbackProcess() {
        if (this.feedbackProcess == TipoNotificacionEnum.FG) {
            return Mono.error(new FeedbackProcessStatusException("You already have feedback generated"));
        }
        return Mono.just(this);
    }
}
