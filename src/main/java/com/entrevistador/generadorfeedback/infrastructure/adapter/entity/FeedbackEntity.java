package com.entrevistador.generadorfeedback.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "feedback")
@AllArgsConstructor
public class FeedbackEntity {
    @Id
    private String uuid;
    private String idEntrevista;
    private String username;
    private List<EntrevistaFeedbackEntity> entrevista;
}
