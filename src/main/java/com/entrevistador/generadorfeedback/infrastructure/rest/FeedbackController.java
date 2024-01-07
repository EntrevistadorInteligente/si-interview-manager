package com.entrevistador.generadorfeedback.infrastructure.rest;

import com.entrevistador.generadorfeedback.application.usescases.FeedbackCreation;

public class FeedbackController {

    private final FeedbackCreation feedbackCreation;

    public FeedbackController(FeedbackCreation feedbackCreation) {
        this.feedbackCreation = feedbackCreation;
    }

}
