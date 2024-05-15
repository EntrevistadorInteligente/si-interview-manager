package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.InterviewTest;
import com.entrevistador.generadorfeedback.domain.model.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import com.entrevistador.generadorfeedback.domain.port.InterviewTestDao;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxIdEntrevista;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class InterviewTestService implements InterviewTest {
    private final InterviewTestDao interviewTestDao;
    private final WebClient.Builder webClient;
    private final WebFluxIdEntrevista webFluxIdEntrevista;
    @Override
    public Flux<SoloPreguntaImp> getPreguntas(String perfil, int limit) {
        //logica para obtener el idEntrevista
        //return this.interviewTestDao.getPreguntas("id",limit);
        //return getIdEntrevista(perfil).flatMapMany(idEntrevista ->  interviewTestDao.getPreguntas(idEntrevista.getId(), limit));
        return interviewTestDao.getPreguntas("662c22602477f7563c14a5c8",3);
    }

    public Mono<IdEntrevista> getIdEntrevista(String perfil){
        return this.webClient.baseUrl("http://localhost:8765").build()
                .get()
                .uri("/api/orquestador/v1/entrevistador/public/entrevista_muestra_id?perfil=".concat(perfil))
                .retrieve()
                .bodyToMono(IdEntrevista.class);
    }
}
