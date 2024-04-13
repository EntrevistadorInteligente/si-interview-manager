package com.entrevistador.generadorfeedback.infrastructure.adapter.entity;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaFeedbackDto;
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
public class Feedback {
    @Id
    private String uuid;
    private String idEntrevista;
    private List<EntrevistaFeedbackDto> feedback;
}
