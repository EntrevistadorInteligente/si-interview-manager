package com.entrevistador.generadorfeedback.infrastructure.beanconfiguration;

import com.entrevistador.generadorfeedback.domain.service.FeedbackConstruccionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesBeanConfiguration {
    @Bean
    public FeedbackConstruccionService feedbackConstruccionService() {
        return new FeedbackConstruccionService();
    }
}
