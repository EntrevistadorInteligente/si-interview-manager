package com.entrevistador.generadorfeedback.utils;

import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedbackMock {

    private static final FeedbackMock INSTANCE = new FeedbackMock();

    private final ObjectMapper mapper = new ObjectMapper();

    public static FeedbackMock getInstance() {
        return INSTANCE;
    }

    public FeedbackEntity getFeedbackEntity() throws IOException {
        return this.mapper.readValue(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mocks/feedback/FeedbackEntity.json"), FeedbackEntity.class);
    }

    public FeedbackEntity getFeedbackEntityUpdated() throws IOException {
        return this.mapper.readValue(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mocks/feedback/FeedbackEntityUpdated.json"), FeedbackEntity.class);
    }

    public Respuesta getRespuesta() throws IOException {
        return this.mapper.readValue(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mocks/feedback/Respuesta.json"), Respuesta.class);
    }

    public Feedback getFeedback() throws IOException {
        return this.mapper.readValue(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("mocks/feedback/Feedback.json"), Feedback.class);
    }
}
