package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.application.usescases.InterviewTest;
import com.entrevistador.generadorfeedback.domain.model.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import com.entrevistador.generadorfeedback.domain.port.InterviewTestDao;
import com.entrevistador.generadorfeedback.infrastructure.properties.EntrevistaPruebaPorperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class InterviewTestService implements InterviewTest {
    private final InterviewTestDao interviewTestDao;
    private final WebClient.Builder webClient;
    private final EntrevistaPruebaPorperties entrevistaPruebaPorperties;
    @Override
    public Flux<SoloPreguntaImp> getPreguntas(String perfil) {
        //return getIdEntrevista(perfil).flatMapMany(idEntrevista ->  interviewTestDao.getPreguntas(idEntrevista.getId(), entrevistaPruebaPorperties.getLimitpreguntas()));
        return interviewTestDao.getPreguntas("662c22602477f7563c14a5c8",entrevistaPruebaPorperties.getLimitpreguntas());
    }

    public Mono<IdEntrevista> getIdEntrevista(String perfil){
        return this.webClient.baseUrl(entrevistaPruebaPorperties.getWebfluxentrevistamuestra().getUrlbase()).build()
                .get()
                .uri(entrevistaPruebaPorperties.getWebfluxentrevistamuestra().getEndpoint().concat(perfil))
                .retrieve()
                .bodyToMono(IdEntrevista.class);
    }
}
