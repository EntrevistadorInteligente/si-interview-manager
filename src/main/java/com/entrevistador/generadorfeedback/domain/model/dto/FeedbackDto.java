package com.entrevistador.generadorfeedback.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private String idEntrevista;
    private List<EntrevistaFeedbackDto> procesoEntrevista;
    public List<String> getRespuestas() {
        List<String> respuestas = new ArrayList<>();
        for (EntrevistaFeedbackDto entrevista : procesoEntrevista) {
            respuestas.add(entrevista.getRespuesta());
        }
        return respuestas;
    }
    public List<String> getPreguntas() {
        List<String> preguntas = new ArrayList<>();
        for (EntrevistaFeedbackDto entrevista : procesoEntrevista) {
            preguntas.add(entrevista.getPregunta());
        }
        return preguntas;
    }
}

