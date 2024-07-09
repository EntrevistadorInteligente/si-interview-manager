package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaFeedbackEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.FeedbackEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackRepository extends ReactiveMongoRepository<FeedbackEntity, String> {
    Mono<FeedbackEntity> findByIdEntrevista(String idEntrevista);

    @Aggregation(pipeline = {"""
            {
                '$match': {
                    'idEntrevista': ?0
                }
            }
            """,
            """
            {
                '$unwind': {
                    'includeArrayIndex': 'index',
                    'preserveNullAndEmptyArrays': true,
                    'path': '$entrevista'
                }
            }
            """,
            """
            {
                '$limit': ?1
            }
            """,
            """
            {
                '$replaceRoot': {
                    'newRoot': {
                        idPregunta: '$entrevista.idPregunta',
                        pregunta: '$entrevista.pregunta',
                        respuesta: '$entrevista.respuesta',
                        feedback: '$entrevista.feedback'
                    }
                }
            }
            """
    })
    Flux<EntrevistaFeedbackEntity> obtenerPreguntas(String idEntrevista, int limit);
}
