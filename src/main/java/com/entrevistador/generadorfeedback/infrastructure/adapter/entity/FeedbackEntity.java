package com.entrevistador.generadorfeedback.infrastructure.adapter.entity;

import com.entrevistador.generadorfeedback.domain.model.enums.TipoNotificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "feedback")
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackEntity {
    @Id
    private String uuid;
    private String idEntrevista;
    private String username;
    private TipoNotificacionEnum feedbackProcess;
    private List<EntrevistaFeedbackEntity> entrevista;
}
