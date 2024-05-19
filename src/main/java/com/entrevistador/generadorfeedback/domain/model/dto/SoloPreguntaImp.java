package com.entrevistador.generadorfeedback.domain.model.dto;

public class SoloPreguntaImp implements SoloPreguntaDto{

    private String pregunta;

    public SoloPreguntaImp(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String getPregunta() {
        return this.pregunta;
    }
}
