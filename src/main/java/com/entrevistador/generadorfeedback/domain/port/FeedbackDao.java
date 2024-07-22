package com.entrevistador.generadorfeedback.domain.port;

import com.entrevistador.generadorfeedback.domain.model.Entrevista;
import com.entrevistador.generadorfeedback.domain.model.Feedback;
import com.entrevistador.generadorfeedback.domain.model.FeedbackResponse;
import com.entrevistador.generadorfeedback.domain.model.Pregunta;
import com.entrevistador.generadorfeedback.domain.model.PreguntaComentarioEntrevista;
import com.entrevistador.generadorfeedback.domain.model.Respuesta;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackDao {
    Mono<Feedback> obtenerFeedback(String entrevistaId);

    Mono<Pregunta> guardarPreguntas(Entrevista entrevista);

    Mono<Respuesta> actualizarRespuestas(Respuesta respuesta);

    Mono<Feedback> actualizarFeedback(Feedback feedback);

    Flux<PreguntaComentarioEntrevista> obtenerPreguntas(String entrevistaId);

    Flux<FeedbackResponse> obtenerEntrevistaFeedback(String entrevistaId);
}
