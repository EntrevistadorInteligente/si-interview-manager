package com.entrevistador.generadorfeedback.infrastructure.repositoy;

import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.Feedback;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface FeedbackRepository extends ReactiveMongoRepository<Feedback, String> {
    Mono<Feedback> findByIdEntrevista(String idEntrevista);

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
                    'path': '$feedback'
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
                        pregunta: '$feedback.pregunta'
                    }
                }
            }
            """
    })
    Flux<SoloPreguntaImp> obtenerPreguntas(String idEntrevista, int limit);
}
